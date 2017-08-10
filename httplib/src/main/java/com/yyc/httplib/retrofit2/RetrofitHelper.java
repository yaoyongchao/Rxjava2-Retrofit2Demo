package com.yyc.httplib.retrofit2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: Page
 * @time: 17-8-4
 * @description:
 */

public class RetrofitHelper {
    private static Retrofit retrofit;

    public RetrofitHelper() {
        this(BaseApi.BASE_URL);
    }

    public RetrofitHelper(String url) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(OkHttpHelper.getInstance())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class RetrofitHolder {
        private final static RetrofitHelper instance = new RetrofitHelper();
    }




        /**
         * @param: [url]
         * @return: retrofit2.Retrofit
         * @description: 此方法使用场景，某个小模块的url 与项目的大部分模块不一样是使用
         */
    public static Retrofit getRetrofit(String url) {
        return new RetrofitHelper(url).retrofit;
    }

    public static Retrofit getRetrofit() {
        return new RetrofitHolder().instance.retrofit;
    }
}
