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

    private String code;

    private String message;

    private Object data;

    public static Result success() {
        return new Result(ResultUtils.SUCCESS_CODE, ResultUtils.SUCCESS_MESSAGE, null);
    }

    public static Result fail() {
        return new Result(ResultUtils.FAIL_CODE, ResultUtils.FAIL_MESSAGE, null);
    }

    public static Result success(Object data) {
        return new Result(ResultUtils.SUCCESS_CODE, ResultUtils.SUCCESS_MESSAGE, data);
    }

    public static Result fail(Object data) {
        return new Result(ResultUtils.FAIL_CODE, ResultUtils.FAIL_MESSAGE, data);
    }

}
