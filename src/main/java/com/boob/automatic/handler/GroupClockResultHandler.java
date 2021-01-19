package com.boob.automatic.handler;

import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.dao.UserDao;
import com.boob.automatic.entity.ClockResult;
import com.boob.automatic.enums.ClockTypeEnum;
import com.boob.automatic.util.Result;
import com.boob.automatic.ytj.YTJRequest;
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
    public void handleSuccessResult(Result result, YTJRequest ytjRequest) {
        ClockResult successClockResult = handleResult(result, ytjRequest)
                //设置操作人id为0，自动操作
                .setOperateUserId(0L)
                .setClockType(ClockTypeEnum.GROUP.getCode())
                .setSuccess(true);
        successClockResults.add(successClockResult);
    }

    @Override
    public void handleFailResult(Result result, YTJRequest ytjRequest) {
        ClockResult failClockResult = handleResult(result, ytjRequest)
                //设置操作人id为0，自动操作
                .setOperateUserId(0L)
                .setClockType(ClockTypeEnum.GROUP.getCode())
                .setSuccess(false);
        failClockResultList.add(failClockResult);
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
