package com.guanyin.sardar.pha.utils;

import android.app.Application;


public class MyApplication extends Application {
    // 整个app都要用到的数据存放在这里

    private static MyApplication instance;

    public static int initApp = 1;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
