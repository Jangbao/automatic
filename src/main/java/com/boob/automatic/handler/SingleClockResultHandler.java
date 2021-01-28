package com.boob.automatic.handler;

import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.entity.ClockResult;

/**
 * @author jangbao - 2021/1/17 20:29
 */
public class SingleClockResultHandler extends ClockResultHandler {

    /**
     * 处理结果
     */
    private ClockResult clockResult;

    public SingleClockResultHandler(ClockResultDao clockResultDao) {
        super(clockResultDao);
    }

    @Override
    public void handleSuccessResult(ClockResult clockResult) {
        this.clockResult = clockResult;
    }

    @Override
    public void handleFailResult(ClockResult clockResult) {
        this.clockResult = clockResult;
    }

    @Override
    public void doHandle() {
        recordClockResult(clockResult);
    }

}
