package com.boob.automatic.builder;

import com.boob.automatic.util.ParamUtils;
import com.boob.automatic.util.Send;

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
    public String getParam() {
        return ParamUtils.jsonParam(this.send.getParams());
    }

    public static class PostJsonSend implements Send {

        private Object params;

        public PostJsonSend(Object params) {
            this.params = params;
        }

        @Override
        public Object getParams() {
            return params;
        }
    }
}
