package com.yyc.httplib.retrofit2;

/**
 * @author: Page
 * @time: 17-8-7
 * @description:
 */

public class ServiceFactory {
    public static <T> T getService(String url ,final  Class<T> service) {
        return RetrofitHelper.getRetrofit(url).create(service);
    }

    public static <T> T getService(final  Class<T> service) {
        return RetrofitHelper.getRetrofit().create(service);
    }
}
