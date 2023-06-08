package com.dreafaa.aohoifsd;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

// 对应着服务端的接口
public interface UserService {
    @Headers("Content-Type:application/json")
    // 如果后端是@RequestParam接受参数，加上@FormUrlEncoded
    // 请求路径前不能加 /
    @POST("/api/app/init")
    Call<Bean> getData(@Body Data data, @Header("X-Requested-With") String packageName);

}


