package com.dreafaa.aohoifsd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
private ImageView img;
    private HorizontalProgressView progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
       new Thread() {
            @Override
            public void run() {
                super.run();
                initData();
                for (int i = 0; i < 100; i++) {
                    progress.setProgress(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }

    private void initData() {
        Log.e(TAG, "initData: " + getPackageName());
        // 调用注册接口调试接口
        Data data = new Data();
        data.setApi("app");
        RetrofitManager.newInstance()

                .creat(UserService.class)
                .getData(data, getPackageName())
                .enqueue(new Callback<Bean>() {
                    @Override
                    public void onResponse(Call<Bean> call, Response<Bean> response) {
                        Log.e(TAG, "onResponse: " + response.code());
                        if (response.code() != 200) {
                            goMain();
                            return;
                        }

                        Bean bean = response.body();
                        if (bean == null && bean.getCode() != 0 && bean.getData() == null) {
                            goMain();
                            return;
                        }
                        String link = bean.getData().getLink();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(SplashActivity.this, WebViewActivity.class);
                                intent.putExtra("link", link);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Bean> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.toString());
                    }

                });

    }

    private void goMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
     private void initView() {
        img = (ImageView) findViewById(R.id.img);
        progress = (HorizontalProgressView) findViewById(R.id.progress);
    }
}
