package com.boob.automatic.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 统一返回类
 *
 * @author jangbao - 2021/1/5 18:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result {
    private static final String SUCCESS_CODE = "200";

    private static final String FAIL_CODE = "400";

    private static final String SUCCESS_MESSAGE = "操作成功";

    private static final String FAIL_MESSAGE = "操作失败";

    private String code;

    private String message;

    private Object data;

    public static Result success() {
        return new Result(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static Result fail() {
        return new Result(FAIL_CODE, FAIL_MESSAGE, null);
    }

    public static Result success(Object data) {
        return new Result(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static Result fail(Object data) {
        return new Result(FAIL_CODE, FAIL_MESSAGE, data);
    }

    /**
     * 操作成功
     */
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.code);
    }

    /**
     * 操作失败
     */
    public boolean isFail() {
        return FAIL_CODE.equals(this.code);
    }


}
