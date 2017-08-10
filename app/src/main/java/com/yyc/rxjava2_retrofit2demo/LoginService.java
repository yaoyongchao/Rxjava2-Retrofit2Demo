package com.yyc.rxjava2_retrofit2demo;

import com.yyc.httplib.entity.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * @author: Page
 * @time: 17-8-10
 * @description:
 */

public interface LoginService {
    @POST("login2")//该接口只作为测试，不用传参数
    Observable<BaseEntity<User>> login();
}
