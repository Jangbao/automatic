package com.boob.automatic.util;

import com.boob.automatic.builder.GetRequestBuilder;
import com.boob.automatic.builder.PostFormRequestBuilder;
import com.boob.automatic.builder.PostJsonRequestBuilder;
import com.boob.automatic.builder.RequestBuilder;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author jangbao - 2021/1/5 18:41
 */
@Log4j2
public class HttpClientUtils {

    /**
     * 默认接收数据编码格式
     */
    private static final Charset DEFAULT_READ_ENCODE = StandardCharsets.UTF_8;
    /**
     * 默认输出数据编码格式
     */
    private static final Charset DEFAULT_WRITE_ENCODE = StandardCharsets.UTF_8;

    /**
     * 发送post 请求
     *
     * @param send    请求对象
     * @param url     请求地址
     * @param isProxy 是否使用代理
     * @return 返回结果
     */
    public static Result sendPostForm(String url, boolean isProxy, PostFormSend send) {
        return sendRequest(url, isProxy, new PostFormRequestBuilder(send));
    }

    /**
     * 发送post 请求
     *
     * @param send 请求对象
     * @param url  请求地址
     * @return 返回结果
     */
    public static Result sendPostForm(String url, PostFormSend send) {
        return sendPostForm(url, false, send);
    }

    /**
     * 发送post json 请求
     *
     * @param send    请求对象
     * @param url     请求地址
     * @param isProxy 是否使用代理
     * @return 返回结果
     */
    public static Result sendPostJson(String url, boolean isProxy, PostJsonSend send) {
        return sendRequest(url, isProxy, new PostJsonRequestBuilder(send));
    }

    /**
     * 发送post json 请求
     *
     * @param send 请求对象
     * @param url  请求地址
     * @return 返回结果
     */
    public static Result sendPostJson(String url, PostJsonSend send) {
        return sendPostJson(url, false, send);
    }

    /**
     * 发送get请求
     *
     * @param send    请求对象
     * @param url     请求地址
     * @param isProxy 是否使用代理
     * @return
     */
    public static Result sendGet(String url, boolean isProxy, GetSend send) {
        return sendRequest(url, isProxy, new GetRequestBuilder(send));
    }

    /**
     * 发送get请求
     *
     * @param send 请求对象
     * @param url  请求地址
     * @return
     */
    public static Result sendGet(String url, GetSend send) {
        return sendGet(url, false, send);
    }


    /**
     * 发送自定义请求
     *
     * @param requestBuilder 请求对象建造器
     * @param url            请求地址
     * @param isProxy        是否使用代理
     * @return
     */
    public static Result sendCustomize(String url, boolean isProxy, RequestBuilder requestBuilder) {
        return sendRequest(url, isProxy, requestBuilder);
    }

    /**
     * 发送自定义请求
     *
     * @param requestBuilder 请求对象建造器
     * @param url            请求地址
     * @return
     */
    public static Result sendCustomize(String url, RequestBuilder requestBuilder) {
        return sendCustomize(url, false, requestBuilder);
    }

    /**
     * 发送请求
     *
     * @param url
     * @param isProxy
     * @param requestBuilder
     * @return
     */
    private static Result sendRequest(String url, boolean isProxy, RequestBuilder requestBuilder) {
        OutputStreamWriter out = null;
        InputStreamReader is = null;
        BufferedReader in = null;
        StringBuilder resultBuilder = new StringBuilder();
        try {
            HttpURLConnection conn = requestBuilder.builder(url, isProxy);
            conn.connect();

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_WRITE_ENCODE);
            out.write(requestBuilder.builderParams());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            is = new InputStreamReader(conn.getInputStream(), DEFAULT_READ_ENCODE);
            in = new BufferedReader(is);
            String line;
            while ((line = in.readLine()) != null) {
                resultBuilder.append(line);
            }
        } catch (Exception e) {
            log.error("发送请求出现异常！" + e);
            return Result.fail(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                log.error("发送请求出现关闭流异常！" + e);
            }
        }
        String result = resultBuilder.toString();
        log.info("发送请求成功,返回信息：" + result);
        return Result.success(result);
    }

}
