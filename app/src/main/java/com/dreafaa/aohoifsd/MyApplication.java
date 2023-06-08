package com.dreafaa.aohoifsd;

import android.app.Application;

import com.appsflyer.AppsFlyerLib;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initApflyer();
    }


    private void initApflyer() {
        AppsFlyerLib appsflyer = AppsFlyerLib.getInstance();
        appsflyer.setMinTimeBetweenSessions(0);
        appsflyer.init("Lfuc6UaPCPhkN3SkoVmv5Q", null, this);
        appsflyer.start(this);

    }
}
