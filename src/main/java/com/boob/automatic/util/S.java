package com.boob.automatic.util;

/**
 * 统一发送类
 *
 * @author jangbao - 2021/1/11 13:38
 */
public interface S<T> {

    /**
     * 获取请求参数
     *
     * @return
     */
    T params();
}
