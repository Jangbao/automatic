package com.boob.automatic.builder;

import com.boob.automatic.util.GetSend;
import com.boob.automatic.util.SendUtils;

import java.net.HttpURLConnection;

/**
 * @author jangbao - 2021/1/5 19:16
 */
public class GetRequestBuilder implements RequestBuilder {

    private GetSend send;

    public GetRequestBuilder(GetSend send) {
        this.send = send;
    }

    @Override
    public HttpURLConnection builder(String url, boolean isProxy) throws Exception {
        return new HttpURLConnectionBuilder(url, isProxy).buildGet();
    }

    @Override
    public String builderParams() {
        return SendUtils.getParam(this.send.params());
    }
}
