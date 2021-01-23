package com.boob.automatic.builder;

import com.boob.automatic.util.SendUtils;
import com.boob.automatic.util.PostJsonSend;

import java.net.HttpURLConnection;

/**
 * @author jangbao - 2021/1/11 13:34
 */
public class PostJsonRequestBuilder implements RequestBuilder {

    private PostJsonSend send;

    public PostJsonRequestBuilder(PostJsonSend send) {
        this.send = send;
    }

    @Override
    public HttpURLConnection builder(String url, boolean isProxy) throws Exception {
        return new HttpURLConnectionBuilder(url, isProxy).buildPostJson();
    }

    @Override
    public String builderParams() {
        return SendUtils.jsonParam(this.send.params());
    }
}
