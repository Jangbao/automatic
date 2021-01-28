package com.boob.automatic.handler;

import com.alibaba.fastjson.JSON;
import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.entity.ClockResult;
import lombok.extern.log4j.Log4j2;


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
     * @param clockResult
     */
    public void handleClockResult(ClockResult clockResult) {
        if (clockResult.isSuccess()) {
            handleSuccessResult(clockResult);
        }
        handleFailResult(clockResult);
    }

    /**
     * 处理成功打卡，并将它记录下来
     *
     * @param clockResult
     */
    public abstract void handleSuccessResult(ClockResult clockResult);

    /**
     * 处理失败打卡，并将它记录下来
     *
     * @param clockResult
     */
    public abstract void handleFailResult(ClockResult clockResult);


    /**
     * 真正做处理的方法
     */
    public abstract void doHandle();

    /**
     * 记录打卡信息
     */
    protected void recordClockResult(ClockResult clockResult) {
        clockResultDao.save(clockResult);
        log.info("记录打卡信息：" + JSON.toJSONString(clockResult));
    }

}
