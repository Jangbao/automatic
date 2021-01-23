package com.boob.automatic.util;

import com.boob.automatic.ytj.YTJResult;

/**
 * 消息常量
 *
 * @author jangbao - 2021/1/19 9:44
 */
public class ResultUtils {


    public static final String SUCCESS_CODE = "200";

    public static final String FAIL_CODE = "400";

    public static final String SUCCESS_MESSAGE = "操作成功";

    public static final String FAIL_MESSAGE = "操作失败";

    /**
     * 以 2 开头代表成功
     */
    public static final String SYMBOL_SUCCESS = "2";

    /**
     * 操作成功
     */
    public static boolean isSuccess(R result) {
        return result.getCode().startsWith(SYMBOL_SUCCESS);
    }

    /**
     * 操作失败
     */
    public static boolean isFail(R result) {
        return !isSuccess(result);
    }

    /**
     * 通过ytjResult获取 result
     *
     * @param ytjResult
     * @return
     */
    public static Result transferYtjResultToResult(YTJResult ytjResult) {
        return new Result().setCode(ytjResult.getCode())
                .setMsg(ytjResult.getMsg())
                .setData("");
    }

}
