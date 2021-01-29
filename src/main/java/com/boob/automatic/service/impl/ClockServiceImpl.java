package com.boob.automatic.service.impl;

import com.alibaba.fastjson.JSON;
import com.boob.automatic.constants.TimeConstants;
import com.boob.automatic.dao.ClockConfigDao;
import com.boob.automatic.dao.ClockInfoDao;
import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.dao.ClockTokenDao;
import com.boob.automatic.dao.UserDao;
import com.boob.automatic.entity.ClockConfig;
import com.boob.automatic.entity.ClockInfo;
import com.boob.automatic.entity.ClockResult;
import com.boob.automatic.entity.ClockToken;
import com.boob.automatic.entity.User;
import com.boob.automatic.enums.ClockTypeEnum;
import com.boob.automatic.enums.OnOffEnum;
import com.boob.automatic.enums.TimeSlotEnum;
import com.boob.automatic.handler.ClockResultHandler;
import com.boob.automatic.handler.GroupClockResultHandler;
import com.boob.automatic.handler.SingleClockResultHandler;
import com.boob.automatic.service.IClockService;
import com.boob.automatic.util.DateUtils;
import com.boob.automatic.util.HttpClientUtils;
import com.boob.automatic.util.Result;
import com.boob.automatic.util.ThreadUtils;
import com.boob.automatic.web.WebUtils;
import com.boob.automatic.ytj.YTJAddress;
import com.boob.automatic.ytj.YTJClockEntity;
import com.boob.automatic.ytj.YTJRequest;
import com.boob.automatic.ytj.YTJRequestBuilder;
import com.boob.automatic.ytj.YTJResult;
import com.boob.automatic.ytj.YTJSend;
import com.boob.automatic.ytj.YTJToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author jangbao - 2021/1/5 18:30
 */
@Service
@Log4j2
public class ClockServiceImpl implements IClockService {

    @Value("${ytj.clock.url}")
    private String ytjClockUrl;

    @Autowired
    private ClockInfoDao clockInfoDao;

    @Autowired
    private ClockConfigDao clockConfigDao;

    @Autowired
    private ClockTokenDao clockTokenDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ClockResultDao clockResultDao;

    /**
     * 并发启动打卡锁
     */
    private final Object lockObject = new Object();

    /**
     * 定时打卡线程池
     */
    private ScheduledExecutorService clockThreadPool;

    @Override
    public boolean cancelClockUser(Long userId) {
        return false;
    }

    @Override
    public boolean addClockUser(Long userId) {
        return false;
    }

    @Override
    public void runClock() {
        synchronized (lockObject) {
            if (threadPoolNotUsable()) {
                //创建线程池
                clockThreadPool = new ScheduledThreadPoolExecutor(1, new CustomizableThreadFactory("clock-scheduled-pool-%d"));
                //给所有用户打卡如果必要
                clockAllIfUserEnable();
                log.info("启动打卡功能 ...");
                return;
            }
            log.info("打卡功能已经处于开启状态，无须进行开启 ...");
        }
    }

    @Override
    public void shutDownClock() {
        synchronized (lockObject) {
            if (threadPoolNotUsable()) {
                log.info("打卡功能已经处于关闭状态，无须进行关闭 ...");
                return;
            }
            clockThreadPool.shutdown();
            log.info("关闭打卡功能 ...");
        }
    }

    @Override
    public boolean isRunning() {
        synchronized (lockObject) {
            boolean isShutdown = threadPoolNotUsable();
            String status = isShutdown ? "关闭" : "开启";
            log.info("打卡功能处于" + status + "状态 ...");
            return !isShutdown;
        }
    }

    @Override
    public String clockByUserId(Long userId) {
        User user = userDao.getOne(userId);
        ClockConfig clockConfig = clockConfigDao.findByUserId(userId);
        ClockInfo clockInfo = clockInfoDao.findByUserId(userId);
        ClockToken clockToken = clockTokenDao.findByUserId(userId);

        SingleClockResultHandler clockResultHandler = new SingleClockResultHandler(clockResultDao);
        ClockResult clockResult = clock(new YTJClockEntity(user, clockConfig, clockInfo, clockToken), ClockTypeEnum.SINGLE);
        clockResultHandler.handleClockResult(clockResult);
        clockResultHandler.doHandle();

        return clockResult.getResponseMessage();
    }

    @Override
    public List<ClockResult> todayGroupClockInfo() {
        return todayInfoByClockType(ClockTypeEnum.GROUP);
    }

    @Override
    public List<ClockResult> todaySingleClockInfo() {
        return todayInfoByClockType(ClockTypeEnum.SINGLE);
    }


    @Override
    public List<ClockResult> todayUserClockInfo(Long userId) {
        return clockResultDao.findByClockUserIdAndClockTimeGreaterThan(userId, DateUtils.today());
    }

    @Override
    public List<ClockResult> userClockInfo(Long userId) {
        return clockResultDao.findByClockUserId(userId);
    }


    /**
     * 线程池是否可用
     */
    private boolean threadPoolNotUsable() {
        return clockThreadPool == null || clockThreadPool.isShutdown();
    }


    /**
     * 通过打卡类型今天打卡的信息
     *
     * @param clockTypeEnum 打卡类型
     */
    private List<ClockResult> todayInfoByClockType(ClockTypeEnum clockTypeEnum) {
        return clockResultDao.findByClockTypeAndClockTimeGreaterThan(clockTypeEnum.getCode(), DateUtils.today());
    }

    /**
     * 给所有用户打卡
     */
    private void clockAllIfUserEnable() {

        //每天早上开始打卡
        long timeToWait = TimeSlotEnum.MORNING.getTimeToWait();
        clockThreadPool.scheduleAtFixedRate(() -> {
            try {
                preProcess();
                ClockResultHandler clockResultHandler = new GroupClockResultHandler(clockResultDao);
                List<YTJClockEntity> entities = getYtjClockEntities();
                for (YTJClockEntity entity : entities) {
                    ClockResult clockResult = clock(entity, ClockTypeEnum.GROUP);
                    clockResultHandler.handleClockResult(clockResult);
                }
                clockResultHandler.doHandle();
                postProcess();
            } catch (Exception e) {
                log.error(e);
            }
        }, timeToWait, TimeConstants.ONE_DAY_TO_SECOND, TimeUnit.SECONDS);

    }

    /**
     * 给用户打卡
     *
     * @param entity        打卡需要的信息
     * @param clockTypeEnum 打卡类型
     */
    private ClockResult clock(YTJClockEntity entity, ClockTypeEnum clockTypeEnum) {
        ClockResult clockResult = new ClockResult();
        //设置操作人为当前用户
        clockResult.setOperateUserId(WebUtils.getUserId());

        YTJRequest ytjRequest = getYtjRequestByYTJClockEntity(entity);
        //如果是集体打卡
        if (ClockTypeEnum.GROUP.equals(clockTypeEnum)) {
            //睡眠一段时间,避免连续发请求
            ThreadUtils.sleepSecondsRandom(TimeConstants.MILLI_SECOND_GAP);
            //集体打卡操作人id 为0
            clockResult.setOperateUserId(0L);
        }

        //发送打卡请求
        Result result = HttpClientUtils.sendCustomize(ytjClockUrl, new YTJRequestBuilder(ytjRequest));

        //解析请求结果
        YTJResult ytjResult = JSON.parseObject(result.getData().toString(), YTJResult.class);

        //打卡结果设置
        clockResult.setRequestMessage(JSON.toJSONString(ytjRequest))
                .setResponseMessage(JSON.toJSONString(ytjResult))
                .setClockTime(new Date())
                .setClockType(clockTypeEnum.getCode())
                .setClockUserId(entity.getUser().getId());

        return clockResult;
    }

    /**
     * 当日打卡前置处理
     */
    private void preProcess() {
        log.info("今日打卡开始 ----->");
    }

    /**
     * 当日打卡后置处理
     */
    private void postProcess() {
        log.info("今日打卡结束 > _ <");
    }

    /**
     * 获取所有需要打卡的用户信息
     */
    private List<YTJClockEntity> getYtjClockEntities() {
        //获取所有用户
        List<User> users = userDao.findAll();
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));

        Map<Long, ClockConfig> enableClockConfigMap = clockConfigDao.findAllByUserIdIn(userMap.keySet()).stream().filter((clockConfig) -> {
            //获取开启打卡配置的用户配置
            return OnOffEnum.getOnOffEnumByCode(clockConfig.getClockOpen()).isOn();
        }).collect(Collectors.toMap(ClockConfig::getUserId, clockConfig -> clockConfig));

        //开启打卡配置的用户id
        Set<Long> enableClockUserIds = enableClockConfigMap.keySet();

        //获取用户打卡info
        Map<Long, ClockInfo> enableClockInfoMap = clockInfoDao.findAllByUserIdIn(enableClockUserIds).stream()
                .collect(Collectors.toMap(ClockInfo::getUserId, clockInfo -> clockInfo));

        //获取用户打卡token
        Map<Long, ClockToken> enableClockTokenMap = clockTokenDao.findAllByUserIdIn(enableClockUserIds).stream()
                .collect(Collectors.toMap(ClockToken::getUserId, clockToken -> clockToken));

        List<YTJClockEntity> entities = new ArrayList<>();
        for (Long enableClockUserId : enableClockUserIds) {
            User user = userMap.get(enableClockUserId);
            ClockConfig clockConfig = enableClockConfigMap.get(enableClockUserId);
            ClockInfo clockInfo = enableClockInfoMap.get(enableClockUserId);
            ClockToken clockToken = enableClockTokenMap.get(enableClockUserId);
            entities.add(new YTJClockEntity(user, clockConfig, clockInfo, clockToken));
        }
        return entities;
    }

    /**
     * 通过YTJClockEntity 获取 ytjClockRequest
     *
     * @param entity
     */
    private YTJRequest getYtjRequestByYTJClockEntity(YTJClockEntity entity) {
        YTJSend ytjSend = new YTJSend();
        YTJToken ytjToken = new YTJToken();
        //复制打卡信息
        ClockInfo clockInfo = entity.getClockInfo();
        BeanUtils.copyProperties(clockInfo, ytjSend);

        //复制地址
        YTJAddress ytjAddress = new YTJAddress();
        BeanUtils.copyProperties(clockInfo.getAddress(), ytjAddress);
        ytjSend.setAddress(ytjAddress);

        //复制token
        BeanUtils.copyProperties(entity.getClockToken(), ytjToken);
        return new YTJRequest(ytjSend, ytjToken);
    }

}
