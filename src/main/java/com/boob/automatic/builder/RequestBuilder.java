package com.boob.automatic.builder;

import java.net.HttpURLConnection;

/**
 * @author jangbao - 2021/1/5 19:13
 */
public interface RequestBuilder {

    /**
     * 构建httpURLConnection
     *
     * @param
     */
    HttpURLConnection builder(String url, boolean isProxy) throws Exception;

    /**
     * 获取请求参数
     *
     * @return
     */
    String builderParams();
}
