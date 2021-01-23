package com.boob.automatic.util;

/**
 * 返回结果接口
 *
 * @author jangbao - 2021/1/20 21:47
 */
public interface R {

    /**
     * 获取返回码
     *
     * @return
     */
    String getCode();

    /**
     * 获取返回消息
     *
     * @return
     */
    String getMsg();

    /**
     * 获取返回数据
     *
     * @return
     */
    Object getData();
}
