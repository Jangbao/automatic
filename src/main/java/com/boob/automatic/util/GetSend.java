package com.boob.automatic.util;

/**
 * @author jangbao - 2021/1/23 12:49
 */
public class GetSend implements S {

    private String params;

    public GetSend() {
        this("");
    }

    public GetSend(String params) {
        this.params = params;
    }

    @Override
    public String params() {
        return params;
    }
}