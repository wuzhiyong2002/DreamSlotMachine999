package com.dreafaa.aohoifsd;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    // 请求的URL前缀
    private static final String BASEURL = "https://juliang123.top";
    private static Retrofit retrofit;
    private static RetrofitManager retrofitManager;

    //提供共有的方法供外界访问
    public static RetrofitManager newInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                retrofitManager = new RetrofitManager();
            }
        }
        return retrofitManager;
    }

    //通过动态代理生成相应的Http请求
    public <T> T creat(Class<T> t) {
        return retrofit.create(t);
    }

    //构造方法私有化
    private RetrofitManager() {
        retrofit = getRetrofit();
    }

    //构建Ok请求
    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
    }

    //构建Retrofit
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}

