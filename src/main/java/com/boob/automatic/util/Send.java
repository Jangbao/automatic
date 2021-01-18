package com.boob.automatic.util;

/**
 * 统一发送类
 *
 * @author jangbao - 2021/1/11 13:38
 */
public interface Send {

    /**
     * 获取参数
     *
     * @return
     */
    <T> T getParams();
}
