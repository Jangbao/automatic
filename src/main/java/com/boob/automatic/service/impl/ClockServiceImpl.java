package com.boob.automatic.service.impl;

import com.alibaba.fastjson.JSON;
import com.boob.automatic.contants.TimeConstants;
import com.boob.automatic.dao.ClockConfigDao;
import com.boob.automatic.dao.ClockInfoDao;
import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.dao.ClockTokenDao;
import com.boob.automatic.dao.UserDao;
import com.boob.automatic.entity.ClockConfig;
import com.boob.automatic.entity.ClockInfo;
import com.boob.automatic.entity.ClockToken;
import com.boob.automatic.entity.User;
import com.boob.automatic.enums.OnOffEnum;
import com.boob.automatic.enums.TimeSlotEnum;
import com.boob.automatic.handler.ClockResultHandler;
import com.boob.automatic.handler.GroupClockResultHandler;
import com.boob.automatic.handler.SingleClockResultHandler;
import com.boob.automatic.service.IClockService;
import com.boob.automatic.util.HttpClientUtils;
import com.boob.automatic.util.Result;
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
import java.util.List;
import java.util.Map;
import java.util.Random;
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
     * 是否已启动打卡
     */
    private boolean isRunClock = false;

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
            if (isRunClock) {
                return;
            }
            //创建线程池
            clockThreadPool = new ScheduledThreadPoolExecutor(1, new CustomizableThreadFactory("clock-scheduled-pool-%d"));
            //给所有用户打卡如果必要
            clockAllIfUserEnable();
            isRunClock = true;
        }
    }

    @Override
    public void shutDownClock() {
        synchronized (lockObject) {
            if (!isRunClock) {
                return;
            }
            if (!clockThreadPool.isShutdown()) {
                clockThreadPool.shutdown();
            }
            isRunClock = false;
        }
    }

    @Override
    public boolean isRunning() {
        synchronized (lockObject) {
            return isRunClock;
        }
    }

    @Override
    public Result clock(Long userId) {
        User user = userDao.getOne(userId);
        ClockConfig clockConfig = clockConfigDao.findByUserId(userId);
        ClockInfo clockInfo = clockInfoDao.findByUserId(userId);
        ClockToken clockToken = clockTokenDao.findByUserId(userId);

        SingleClockResultHandler clockResultHandler = new SingleClockResultHandler(clockResultDao);
        Result result = clock(new YTJClockEntity(user, clockConfig, clockInfo, clockToken), false, clockResultHandler);
        clockResultHandler.doHandle();

        return result;
    }

    /**
     * 给所有用户打卡
     */
    private void clockAllIfUserEnable() {

        //每天早上开始打卡
        long timeToWait = TimeSlotEnum.MORNING.getTimeToWait();
        clockThreadPool.scheduleAtFixedRate(() -> {
            preProcess();
            ClockResultHandler clockResultHandler = new GroupClockResultHandler(clockResultDao);
            List<YTJClockEntity> entities = getYtjClockEntities();
            for (YTJClockEntity entity : entities) {
                clock(entity, true, clockResultHandler);
            }
            clockResultHandler.doHandle();
            postProcess();
        }, timeToWait, TimeConstants.ONE_DAY_TO_SECOND, TimeUnit.SECONDS);

    }

    /**
     * 给用户打卡
     *
     * @param entity             打卡需要的信息
     * @param groupClock         集体打卡
     * @param clockResultHandler 打卡结果处理器
     */
    private Result clock(YTJClockEntity entity, boolean groupClock, ClockResultHandler clockResultHandler) {
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
        //如果是集体打卡
        if (groupClock) {
            Random random = new Random();
            try {
                //等待随机秒数后继续下一次打卡
                TimeUnit.SECONDS.sleep(random.nextInt(TimeConstants.SECOND_GAP));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        YTJRequest ytjRequest = new YTJRequest(ytjSend, ytjToken);

        Result result = HttpClientUtils.sendCustomize(ytjClockUrl, new YTJRequestBuilder(ytjRequest));

        YTJResult ytjResult = JSON.parseObject(result.getData().toString(), YTJResult.class);
        //处理请求结果
        clockResultHandler.handleClockResult(ytjResult, ytjRequest, entity.getUser());
        return result.setData("");
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

}
