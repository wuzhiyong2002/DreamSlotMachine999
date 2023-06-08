package com.dreafaa.aohoifsd;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.appsflyer.AppsFlyerLib;
import com.dreafaa.aohoifsd.config.Config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SingletionDLC {
    private static final String TAG = "SingletionDLC";
    private volatile static SingletionDLC mInstance;
    private Context mContext;

    private SingletionDLC() {
    }

    public static SingletionDLC getmInstance() {
        if (mInstance == null) {
            synchronized (SingletionDLC.class) {
                if (mInstance == null) {
                    mInstance = new SingletionDLC();

                }
            }
        }
        return mInstance;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWebView(WebView webView, String url, Context context) {
        mContext = context;
        initThird();
        webView.addJavascriptInterface(new JsInterface(), "jsBridge");
        webView.loadUrl(url);
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setAllowFileAccess(false);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setVisibility(View.VISIBLE);
            }
        });

    }


    private boolean isAvilible(Context context, String packageName) {

        final PackageManager packageManager = context.getPackageManager();

        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {

            // 循环判断是否存在指定包名
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName)) {
                return true;
            }

        }
        return false;
    }

    private void initThird() {
        initAppsFlyer();
    }


    private void initAppsFlyer() {
        AppsFlyerLib appsflyer = AppsFlyerLib.getInstance();
        appsflyer.setMinTimeBetweenSessions(0);
        appsflyer.init(Config.APPSFLYER_KEY, null, mContext);
        appsflyer.start(mContext);

    }


    class JsInterface {

        public JsInterface() {
        }

        @JavascriptInterface
        public void postMessage(String method, String data) {
            Log.e(TAG, "method: " + method);
            Log.e(TAG, "data: " + data);
            Map<String, Object> eventParameters0 = new HashMap<String, Object>();
            switch (method) {
                case "login":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_login", eventParameters0);
                    break;
                case "logout":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_logout", eventParameters0);
                    break;
                case "registerClick":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_registerClick", eventParameters0);
                    break;
                case "register":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_register", eventParameters0);
                    break;
                case "rechargeClick":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_rechargeClick", eventParameters0);
                    break;
                case "firstrecharge":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_firstrecharge", eventParameters0);
                    break;
                case "recharge":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_recharge", eventParameters0);
                    break;
                case "withdrawClick":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_withdrawClick", eventParameters0);
                    break;
                case "withdrawOrderSuccess":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_withdrawOrderSuccess", eventParameters0);
                    break;
                case "enterGame":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_enterGame", eventParameters0);
                    break;
                case "vipReward":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_vipReward", eventParameters0);
                    break;
                case "dailyReward":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_dailyReward", eventParameters0);
                    break;
                case "enterEventCenter":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_enterEventCenter", eventParameters0);
                    break;
                case "enterTask":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_enterTask", eventParameters0);
                    break;
                case "enterCashback":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_enterCashback", eventParameters0);
                    break;
                case "enterPromote":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_enterPromote", eventParameters0);
                    break;
                case "bannerClick":
                    AppsFlyerLib.getInstance().logEvent(mContext, "af_bannerClick", eventParameters0);
                    break;


/*
我司定义的事件名称如下 :
登录 :“login”
登出:“logout”
点击注册 :“registerClick”
注册成功 :“register”
点击充值 :“rechargeClick"
首充成功 :“firstrecharge'
*复充成功 :“recharge"
提现点击 :“withdrawClick”提现成功 :“withdrawOrderSuccess”进入游戏(包含三方与自营):“enterGame领取 vip 奖励:“vipReward”领取每日奖励:“dailyReward”
新增交互事件
1.活动中心(进入页面) :“enterEventCenter
2.任务中心(进入页面) :“enterTask”
3.实时返水(进入页面):“enterCashback
4.推广赚钱(进入页面) :“enterPromote”
5.6张 banner图(每张图的点击事件) :“bannerClick
* */

            }
        }

    }
}