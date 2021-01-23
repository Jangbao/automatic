package com.boob.automatic.handler;

import com.boob.automatic.dao.ClockResultDao;
import com.boob.automatic.entity.ClockResult;
import com.boob.automatic.entity.User;
import com.boob.automatic.enums.ClockTypeEnum;
import com.boob.automatic.web.WebUtils;
import com.boob.automatic.ytj.YTJRequest;
import com.boob.automatic.ytj.YTJResult;

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
    public void handleSuccessResult(YTJResult ytjResult, YTJRequest ytjRequest, User user) {
        clockResult = handleResult(ytjResult, ytjRequest)
                .setOperateUserId(WebUtils.getUserId())
                .setClockType(ClockTypeEnum.SINGLE.getCode())
                .setClockUserId(user.getId())
                .setSuccess(true);
    }

    @Override
    public void handleFailResult(YTJResult ytjResult, YTJRequest ytjRequest, User user) {
        clockResult = handleResult(ytjResult, ytjRequest)
                .setOperateUserId(WebUtils.getUserId())
                .setClockType(ClockTypeEnum.SINGLE.getCode())
                .setClockUserId(user.getId())
                .setSuccess(false);
    }

    @Override
    public void doHandle() {
        recordClockResult(clockResult);
    }

}
