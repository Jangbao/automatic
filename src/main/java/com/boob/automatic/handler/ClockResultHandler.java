package com.boob.automatic.handler;

import com.alibaba.fastjson.JSON;
import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.entity.ClockResult;
import com.boob.automatic.entity.User;
import com.boob.automatic.util.ResultUtils;
import com.boob.automatic.ytj.YTJRequest;
import com.boob.automatic.ytj.YTJResult;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

/**
 * @author jangbao - 2021/1/16 23:03
 */
@Log4j2
public abstract class ClockResultHandler {

    protected ClockResultDao clockResultDao;

    public ClockResultHandler(ClockResultDao clockResultDao) {
        this.clockResultDao = clockResultDao;
    }

    /**
     * 处理打卡结果信息
     *
     * @param ytjResult
     * @param ytjRequest
     * @return
     */
    public void handleClockResult(YTJResult ytjResult, YTJRequest ytjRequest, User user) {
        if (ResultUtils.isSuccess(ytjResult)) {
            handleSuccessResult(ytjResult, ytjRequest, user);
        }
        handleFailResult(ytjResult, ytjRequest, user);
    }

    /**
     * 处理成功打卡，并将它记录下来
     *
     * @param result
     * @param ytjRequest
     * @param user
     */
    public abstract void handleSuccessResult(YTJResult result, YTJRequest ytjRequest, User user);

    /**
     * 处理失败打卡，并将它记录下来
     *
     * @param result
     * @param ytjRequest
     * @param user
     */
    public abstract void handleFailResult(YTJResult result, YTJRequest ytjRequest, User user);


    /**
     * 真正做处理的方法
     */
    public abstract void doHandle();


    /**
     * 处理打卡
     *
     * @param ytjResult
     * @param ytjRequest
     * @return
     */
    protected ClockResult handleResult(YTJResult ytjResult, YTJRequest ytjRequest) {
        return new ClockResult()
                .setRequestMessage(JSON.toJSONString(ytjRequest))
                .setResponseMessage(JSON.toJSONString(ytjResult))
                .setClockTime(new Date());
    }

    /**
     * 记录打卡信息
     */
    protected void recordClockResult(ClockResult clockResult) {
        clockResultDao.save(clockResult);
        log.info("记录打卡信息：" + JSON.toJSONString(clockResult));
    }

}
