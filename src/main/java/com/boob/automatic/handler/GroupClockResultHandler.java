package com.boob.automatic.handler;

import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.entity.ClockResult;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jangbao - 2021/1/17 20:28
 */
@EqualsAndHashCode(callSuper = true)
public class GroupClockResultHandler extends ClockResultHandler {

    /**
     * 成功结果
     */
    private List<ClockResult> successClockResults;

    /**
     * 失败结果
     */
    private List<ClockResult> failClockResultList;


    public GroupClockResultHandler(ClockResultDao clockResultDao) {
        super(clockResultDao);
        this.successClockResults = new ArrayList<>();
        this.failClockResultList = new ArrayList<>();
    }

    @Override
    public void handleSuccessResult(ClockResult clockResult) {
        successClockResults.add(clockResult);
    }

    @Override
    public void handleFailResult(ClockResult clockResult) {
        failClockResultList.add(clockResult);
    }

    @Override
    public void doHandle() {
        for (ClockResult successClockResult : successClockResults) {
            recordClockResult(successClockResult);
        }
        for (ClockResult failClockResult : failClockResultList) {
            recordClockResult(failClockResult);
        }
    }
}
