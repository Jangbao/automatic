package com.boob.automatic.util;

import java.util.Map;

/**
 * @author jangbao - 2021/1/23 12:48
 */
public class PostFormSend implements S {

    private Map<String, String> params;

    public PostFormSend(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public Map<String, String> params() {
        return params;
    }
}