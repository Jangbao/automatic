package com.boob.automatic.handler;

import com.boob.automatic.entity.FailClockResult;
import com.boob.automatic.entity.SuccessClockResult;
import com.boob.automatic.enums.ClockTypeEnum;
import com.boob.automatic.util.Result;
import com.boob.automatic.ytj.YTJRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jangbao - 2021/1/17 20:28
 */
public class GroupClockResultHandler extends ClockResultHandler {

    /**
     * 成功结果
     */
    private List<SuccessClockResult> successClockResults;

    /**
     * 失败结果
     */
    private List<FailClockResult> failClockResultList;

    public GroupClockResultHandler() {
        this.successClockResults = new ArrayList<>();
        this.failClockResultList = new ArrayList<>();
    }

    @Override
    public void handleSuccessResult(Result result, YTJRequest ytjRequest) {
        SuccessClockResult successClockResult = handleSuccess(result, ytjRequest);
        successClockResult.setClockType(ClockTypeEnum.GROUP.getCode());
        successClockResults.add(successClockResult);
    }

    @Override
    public void handleFailResult(Result result, YTJRequest ytjRequest) {
        FailClockResult failClockResult = handleFail(result, ytjRequest);
        failClockResult.setClockType(ClockTypeEnum.GROUP.getCode());
        failClockResultList.add(failClockResult);
    }

    @Override
    public void doHandle() {
        for (SuccessClockResult successClockResult : successClockResults) {
            recordSuccess(successClockResult);
        }
        for (FailClockResult failClockResult : failClockResultList) {
            recordFail(failClockResult);
        }
    }
}
