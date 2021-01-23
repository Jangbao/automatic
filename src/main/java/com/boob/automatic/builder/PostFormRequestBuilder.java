package com.boob.automatic.builder;

import com.boob.automatic.util.SendUtils;
import com.boob.automatic.util.PostFormSend;

import java.net.HttpURLConnection;

/**
 * @author jangbao - 2021/1/11 13:14
 */
public class PostFormRequestBuilder implements RequestBuilder {

    private PostFormSend send;

    public PostFormRequestBuilder(PostFormSend send) {
        this.send = send;
    }

    @Override
    public HttpURLConnection builder(String url, boolean isProxy) throws Exception {
        return new HttpURLConnectionBuilder(url, isProxy).buildPostForm();
    }

    @Override
    public String builderParams() {
        return SendUtils.formParam(this.send.params());
    }
}
