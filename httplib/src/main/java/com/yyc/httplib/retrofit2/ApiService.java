package com.yyc.httplib.retrofit2;

/**
 * @author: Page
 * @time: 17-8-4
 * @description: demo 供被依赖者参考
 */

public interface ApiService {

//    @GET("login")
//    Call<You> login();

//    @GET("/weather")
//    WeatherData getWeather(@Query("APPID") String AppID,@Query("q") String place, @Query("units") String units);

//    @FormUrlEncoded
//    @POST("/cuslogin")
//    Observable<Chni_User>  login (@Field("data") String data);

    //    1.1.get请求：通过@GET注解，指明访问的地址
//    @GET("getIpInfo.php")//★这里最前面不能带“/”
    //    1.2.定义一个请求网络，并且返回结果的方法（方法返回Call<IP> ），
//      通过@Query指定key，后面跟上value
//    Call<IP> getIP(@Query("ip") String ip);
    /*********************************************************************************/
//    1.3.post请求：通过@FormUrlEncoded、和@POST注解，指明访问的地址
//        (★注意：千万别忘了@FormUrlEncoded)
//    我们可以使用@FormUrlEncoded注解来发送表单数据。使用 @Field注解和参数来指定每个表单项的Key，value为参数的值。
//    @FormUrlEncoded
//    @POST("getIpInfo.php")
//    1.4.通过@Field来指定key，后面跟上value
//    Call<IP> postIP(@Field("ip") String ip);

//    @Body:用于POST请求体，将实例对象根据转换方式转换为对应的json字符串参数，
//    这个转化方式是GsonConverterFactory定义的。
}
