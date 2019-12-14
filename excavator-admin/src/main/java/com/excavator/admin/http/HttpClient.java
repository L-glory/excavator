package com.excavator.admin.http;

import okhttp3.OkHttpClient;

/**
 * OK HTTP Client connPool测试
 *
 * @author Glory
 * @create 2019-11-25 23:59
 **/
public class HttpClient {

    private OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();
}
