package com.boob.automatic.util;

/**
 * @author jangbao - 2021/1/23 12:48
 */
public  class PostJsonSend implements S {

    private Object params;

    public PostJsonSend(Object params) {
        this.params = params;
    }

    @Override
    public Object params() {
        return params;
    }
}