package com.yyc.httplib.retrofit2;


import com.yyc.httplib.utils.InterceptorUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author: Page
 * @time: 17-8-4
 * @description:
 */

public class OkHttpHelper {
    private static OkHttpClient okHttpClient;

    public OkHttpHelper() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(BaseApi.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(BaseApi.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(BaseApi.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                /*.cache(new Cache(new File(CacheUtil.getHttpCacheDir(context), Api.OKCLIENT_DISK_CACHE_NAME),
                        Api.OKCLIENT_DISK_CACHE_SIZE))*/
                .build(); //设置缓存目录和20M缓存
    }

    private static class OkHttpHolder {
        private final static OkHttpHelper instance = new OkHttpHelper();
    }

    public static OkHttpClient getInstance() {
        return OkHttpHolder.instance.okHttpClient;
    }
}
