package com.boob.automatic.builder;

/**
 * @author jangbao - 2021/1/5 19:13
 */

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

/**
 * HttpURLConnection 建造器
 *
 * @author Jangbao
 */
public class HttpURLConnectionBuilder {

    /**
     * 代理网站
     */
    private static final String PROXY_HOST = "127.0.0.1";

    /**
     * 代理端口
     */
    private static final int PROXY_PORT = 8087;


    /**
     * 默认连接超时时间
     */
    private static final int DEFAULT_CONNECTION_TIMEOUT = 3000;
    /**
     * 默认读取超时时间
     */
    private static final int DEFAULT_READ_TIMEOUT = 3000;

    private HttpURLConnection conn;

    /**
     * @param url     请求url
     * @param isProxy 是否使用代理
     * @throws Exception
     */
    public HttpURLConnectionBuilder(String url, boolean isProxy) throws Exception {
        URL realUrl = new URL(url);
        HttpURLConnection conn;
        //使用代理模式
        if (isProxy) {
            conn = getProxyConnection(realUrl);
        } else {
            conn = (HttpURLConnection) (realUrl.openConnection());
        }
        this.conn = conn;
    }

    /**
     * 获取代理链接
     *
     * @param url 请求url
     * @return
     * @throws IOException
     */
    public HttpURLConnection getProxyConnection(URL url) throws IOException {
        HttpURLConnection conn;
        Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
        conn = (HttpURLConnection) (url.openConnection(proxy));
        return conn;
    }

    /**
     * 设置readTimeout
     *
     * @param header     头标签
     * @param properties 属性
     * @return
     * @throws Exception
     */
    public HttpURLConnectionBuilder setHeader(String header, String properties) throws Exception {
        conn.setRequestProperty(header, properties);
        return this;
    }

    /**
     * 设置readTimeout
     *
     * @param readTimeout
     * @return
     * @throws Exception
     */
    public HttpURLConnectionBuilder readTimeout(int readTimeout) throws Exception {
        conn.setReadTimeout(readTimeout);
        return this;
    }

    /**
     * 设置connectionTimeout
     *
     * @param connectionTimeout
     * @return
     * @throws Exception
     */
    public HttpURLConnectionBuilder connectionTimeout(int connectionTimeout) throws Exception {
        conn.setConnectTimeout(connectionTimeout);
        return this;
    }

    /**
     * 设置通用请求属性，伪装浏览器
     *
     * @return
     * @throws Exception
     */
    public HttpURLConnectionBuilder fakeBrowser() throws Exception {
        // 设置通用的请求属性
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        return this;
    }

    /**
     * 发送post 请求
     *
     * @return
     * @throws Exception
     */
    private HttpURLConnectionBuilder post() throws Exception {
        conn.setRequestMethod("POST");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        return this;
    }


    /**
     * 发送post form 请求
     *
     * @return
     * @throws Exception
     */
    public HttpURLConnectionBuilder postForm() throws Exception {
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        return this.post();
    }


    /**
     * 发送post json请求
     *
     * @return
     * @throws Exception
     */
    public HttpURLConnectionBuilder postJson() throws Exception {
        conn.setRequestProperty("Content-Type", "application/json");
        return this.post();
    }

    /**
     * 发送get 请求
     *
     * @return
     * @throws Exception
     */
    public HttpURLConnectionBuilder get() throws Exception {
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "text/html");
        return this;
    }

    /**
     * 构建请求
     *
     * @return
     */
    public HttpURLConnection build() {
        return conn;
    }

    /**
     * 构建基础请求
     *
     * @return
     * @throws Exception
     */
    public HttpURLConnectionBuilder buildBasic() throws Exception {
        return this.fakeBrowser()
                .connectionTimeout(DEFAULT_CONNECTION_TIMEOUT)
                .readTimeout(DEFAULT_READ_TIMEOUT);
    }

    /**
     * 构建get请求
     *
     * @return
     * @throws Exception
     */
    public HttpURLConnection buildGet() throws Exception {
        return this.buildBasic()
                .get()
                .build();
    }

    /**
     * 构建postJson请求
     *
     * @return
     * @throws Exception
     */
    public HttpURLConnection buildPostJson() throws Exception {
        return this.buildBasic()
                .postJson()
                .build();
    }

    /**
     * 构建post请求
     *
     * @return
     * @throws Exception
     */
    public HttpURLConnection buildPostForm() throws Exception {
        return this.buildBasic()
                .postForm()
                .build();
    }


}
