package com.boob.automatic.util;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 参数工具类
 *
 * @author jangbao - 2021/1/12 22:23
 */
public class ParamUtils {

    private static final String PARAM_ENCODING = "UTF-8";

    /**
     * form 参数转string
     *
     * @param params
     * @return
     */
    public static String formParam(Map<String, String> params) {
        if (params != null && params.size() > 0) {
            StringBuilder encodedParams = new StringBuilder();
            try {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    encodedParams.append(URLEncoder.encode(entry.getKey(), PARAM_ENCODING));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode(entry.getValue(), PARAM_ENCODING));
                    encodedParams.append('&');
                }
                return encodedParams.toString();
            } catch (UnsupportedEncodingException uee) {
                throw new RuntimeException("Encoding not supported: " + PARAM_ENCODING, uee);
            }
        }
        return "";
    }


    /**
     * json 参数转string
     *
     * @param object
     * @return
     */
    public static String jsonParam(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * get 参数转string
     *
     * @param str
     * @return
     */
    public static String getParam(String str) {
        return str;
    }
}
