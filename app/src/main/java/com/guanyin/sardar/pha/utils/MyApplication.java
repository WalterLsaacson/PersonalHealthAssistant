package com.guanyin.sardar.pha.utils;

import android.app.Application;
import android.content.SharedPreferences;


public class MyApplication extends Application {

    private static MyApplication instance;
    public SharedPreferences sp;

    @Override
    public void onCreate() {

        super.onCreate();

        instance = this;
        init();
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    private void init() {
        sp = getSharedPreferences("tips", MODE_PRIVATE);
    }

}
