package com.boob.automatic.handler;

import com.boob.automatic.entity.FailClockResult;
import com.boob.automatic.entity.SuccessClockResult;
import com.boob.automatic.util.Result;
import com.boob.automatic.ytj.YTJRequest;

/**
 * @author jangbao - 2021/1/16 23:03
 */
public abstract class ClockResultHandler {


    /**
     * 处理打卡结果信息
     *
     * @param result
     * @param ytjRequest
     * @return
     */
    public boolean handleClockResult(Result result, YTJRequest ytjRequest) {
        if (result.isSuccess()) {
            handleSuccessResult(result, ytjRequest);
            return true;
        }
        handleFailResult(result, ytjRequest);
        return false;
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
     * 真正做处理的类
     */
    public abstract void doHandle();


    /**
     * 处理成功打卡
     *
     * @param result
     * @param ytjRequest
     * @return
     */
    protected SuccessClockResult handleSuccess(Result result, YTJRequest ytjRequest) {
        return new SuccessClockResult();
    }


    /**
     * 处理失败打卡
     *
     * @param result
     * @param ytjRequest
     * @return
     */
    protected FailClockResult handleFail(Result result, YTJRequest ytjRequest) {
        return new FailClockResult();
    }

    /**
     * 记录成功打卡
     */
    protected void recordSuccess(SuccessClockResult successClockResult) {
    }

    /**
     * 记录失败打卡
     */
    protected void recordFail(FailClockResult failClockResult) {
    }


}
