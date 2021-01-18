package com.boob.automatic.builder;

import com.boob.automatic.util.ParamUtils;
import com.boob.automatic.util.Send;

import java.net.HttpURLConnection;
import java.util.Map;

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
    public String getParam() {
        return ParamUtils.formParam(this.send.getParams());
    }

    public static class PostFormSend implements Send {

        private Map<String, String> params;

        public PostFormSend(Map<String, String> params) {
            this.params = params;
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }
}
