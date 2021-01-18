package com.boob.automatic.ytj;

import com.boob.automatic.builder.HttpURLConnectionBuilder;
import com.boob.automatic.builder.RequestBuilder;
import com.boob.automatic.util.ParamUtils;

import java.net.HttpURLConnection;

/**
 * 易统计请求打卡
 *
 * @author jangbao - 2021/1/12 22:01
 */
public class YTJRequestBuilder implements RequestBuilder {

    /**
     * 易统计打卡请求
     */
    private YTJRequest ytjRequest;


    public YTJRequestBuilder(YTJRequest ytjRequest) {
        this.ytjRequest = ytjRequest;
    }

    @Override
    public HttpURLConnection builder(String url, boolean isProxy) throws Exception {
        return new HttpURLConnectionBuilder(url, isProxy)
                .setHeader("ncov-access-token", ytjRequest.getYtjToken().getToken())
                .buildPostJson();
    }

    @Override
    public String getParam() {
        return ParamUtils.jsonParam(ytjRequest.getYtjSend().getParams());
    }
}
