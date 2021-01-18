package com.boob.automatic.handler;

import com.boob.automatic.entity.FailClockResult;
import com.boob.automatic.entity.SuccessClockResult;
import com.boob.automatic.enums.ClockTypeEnum;
import com.boob.automatic.util.Result;
import com.boob.automatic.ytj.YTJRequest;

/**
 * @author jangbao - 2021/1/17 20:29
 */
public class SingleClockResultHandler extends ClockResultHandler {

    private SuccessClockResult successClockResult;

    private FailClockResult failClockResult;

    private boolean isSuccess;

    @Override
    public void handleSuccessResult(Result result, YTJRequest ytjRequest) {
        successClockResult = handleSuccess(result, ytjRequest);
        successClockResult.setClockType(ClockTypeEnum.SINGLE.getCode());
        this.isSuccess = true;
    }

    @Override
    public void handleFailResult(Result result, YTJRequest ytjRequest) {
        failClockResult = handleFail(result, ytjRequest);
        failClockResult.setClockType(ClockTypeEnum.SINGLE.getCode());
        this.isSuccess = false;
    }

    @Override
    public void doHandle() {
        if (isSuccess) {
            recordSuccess(successClockResult);
        } else {
            recordFail(failClockResult);
        }
    }


}
