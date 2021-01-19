package com.boob.automatic.util;

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
     * 操作成功
     */
    public static boolean isSuccess(String code) {
        return SUCCESS_CODE.equals(code);
    }

    /**
     * 操作失败
     */
    public static boolean isFail(String code) {
        return FAIL_CODE.equals(code);
    }
}
