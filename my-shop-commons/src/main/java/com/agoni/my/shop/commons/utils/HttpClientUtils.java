/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: HttpClientUtils
 * Author:   Soulmate
 * Date:     2019/8/31 14:51
 * Description: HttpClient工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.agoni.my.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;


/**
 * 〈一句话功能简述〉<br>
 * 〈HttpClient工具类〉
 *
 * @author Soulmate
 * @create 2019/8/31
 * @since 1.0.0
 */
public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36";

    /**
     * GET请求
     *
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url) {
        return createRequest(GET, url, null, null);
    }

    /**
     * GET请求(带cookie)
     *
     * @param url    请求地址
     * @param cookie cookie
     * @return
     */
    public static String doGet(String url, String cookie) {
        return createRequest(GET, url, cookie, null);
    }

    /**
     * POST请求
     *
     * @param url    请求地址
     * @param params 请求参数(可选)
     * @return
     */
    public static String doPost(String url, BasicNameValuePair... params) {
        return createRequest(POST, url, null, params);
    }

    /**
     * POST请求(带cookie)
     *
     * @param url    请求地址
     * @param cookie cookie
     * @param params 请求参数
     * @return
     */
    public static String doPost(String url, String cookie, BasicNameValuePair... params) {
        return createRequest(POST, url, cookie, params);
    }

    /**
     * 创建请求
     *
     * @param requestMethod 请求方式 GET/POST
     * @param url           请求地址
     * @param cookie        cookie
     * @param params        请求参数 仅限于POST请求用
     * @return
     */
    private static String createRequest(String requestMethod, String url, String cookie, BasicNameValuePair... params) {
        String result = null;
        //创建 HTTPClient 客户端，打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // ctrl + alt + t 快捷键
        try {
            //响应
            CloseableHttpResponse httpResponse = null;
            //请求方式
            HttpGet httpGet = null;
            HttpPost httpPost = null;
            //请求结果
            result = null;
            //get请求
            if (GET.equals(requestMethod)) {
                httpGet = new HttpGet(url);
                httpGet.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                httpGet.setHeader("Cookie", cookie);
                httpResponse = httpClient.execute(httpGet);
            }
            //post请求
            else if (POST.equals(requestMethod)) {
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                httpPost.setHeader("Cookie", cookie);

                //有参数进来
                if (params != null && params.length > 0) {

                    //将数组转换为list集合，封装到向后端提交数据的entity中
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));
                }
                httpResponse = httpClient.execute(httpPost);
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
