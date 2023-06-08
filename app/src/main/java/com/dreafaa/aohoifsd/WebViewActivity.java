package com.dreafaa.aohoifsd;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        initData();
    }

    private void initData() {
        String link = getIntent().getStringExtra("link");
        SingletionDLC singletionDLC = SingletionDLC.getmInstance();
        //https://ffbet05.com
        singletionDLC.initWebView(web, "https://ffbet05.com", this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) {
            web.goBack();// activityBaseWebAddWebView.reload();
            web.removeAllViews();//删除webview中所以进程
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void initView() {
        web = (WebView) findViewById(R.id.web);
    }
}