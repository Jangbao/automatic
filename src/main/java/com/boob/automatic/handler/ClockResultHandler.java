package com.boob.automatic.handler;

import com.alibaba.fastjson.JSON;
import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.entity.ClockResult;
import com.boob.automatic.util.Result;
import com.boob.automatic.util.ResultUtils;
import com.boob.automatic.ytj.YTJRequest;
import com.boob.automatic.ytj.YTJResult;

import java.util.Date;

/**
 * @author jangbao - 2021/1/16 23:03
 */
public abstract class ClockResultHandler {

    protected ClockResultDao clockResultDao;

    public ClockResultHandler(ClockResultDao clockResultDao) {
        this.clockResultDao = clockResultDao;
    }

    /**
     * 处理打卡结果信息
     *
     * @param result
     * @param ytjRequest
     * @return
     */
    public YTJResult handleClockResult(Result result, YTJRequest ytjRequest) {
        if (ResultUtils.isSuccess(result.getCode())) {
            handleSuccessResult(result, ytjRequest);
        }
        handleFailResult(result, ytjRequest);
        return JSON.parseObject(result.getData().toString(), YTJResult.class);
    }

    /**
     * 处理成功打卡，并将它记录下来
     *
     * @param result
     * @param ytjRequest
     */
    public abstract void handleSuccessResult(Result result, YTJRequest ytjRequest);

    /**
     * 处理失败打卡，并将它记录下来
     *
     * @param result
     * @param ytjRequest
     */
    public abstract void handleFailResult(Result result, YTJRequest ytjRequest);


    /**
     * 真正做处理的方法
     */
    public abstract void doHandle();


    /**
     * 处理打卡
     *
     * @param result
     * @param ytjRequest
     * @return
     */
    protected ClockResult handleResult(Result result, YTJRequest ytjRequest) {
        return new ClockResult()
                .setRequestMessage(JSON.toJSONString(ytjRequest))
                .setResponseMessage(JSON.toJSONString(result))
                .setClockTime(new Date());
    }

    /**
     * 记录打卡信息
     */
    protected void recordClockResult(ClockResult clockResult) {
        clockResultDao.save(clockResult);
    }

}
