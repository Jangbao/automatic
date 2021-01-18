package com.boob.automatic.builder;

import com.boob.automatic.util.ParamUtils;
import com.boob.automatic.util.Send;

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
    public String getParam() {
        return ParamUtils.getParam(this.send.getParams());
    }

    public static class GetSend implements Send {

        private String params;

        public GetSend() {
            this("");
        }

        public GetSend(String params) {
            this.params = params;
        }

        @Override
        public String getParams() {
            return params;
        }
    }
}
