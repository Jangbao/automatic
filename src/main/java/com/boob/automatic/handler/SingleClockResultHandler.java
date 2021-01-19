package com.boob.automatic.handler;

import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.entity.ClockResult;
import com.boob.automatic.enums.ClockTypeEnum;
import com.boob.automatic.util.Result;
import com.boob.automatic.web.WebUtils;
import com.boob.automatic.ytj.YTJRequest;

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
    public void handleSuccessResult(Result result, YTJRequest ytjRequest) {
        clockResult = handleResult(result, ytjRequest)
                .setOperateUserId(WebUtils.getUserId())
                .setClockType(ClockTypeEnum.SINGLE.getCode())
                .setSuccess(true);
    }

    @Override
    public void handleFailResult(Result result, YTJRequest ytjRequest) {
        clockResult = handleResult(result, ytjRequest)
                .setOperateUserId(WebUtils.getUserId())
                .setClockType(ClockTypeEnum.SINGLE.getCode())
                .setSuccess(false);
    }

    @Override
    public void doHandle() {
        recordClockResult(clockResult);
    }

}
