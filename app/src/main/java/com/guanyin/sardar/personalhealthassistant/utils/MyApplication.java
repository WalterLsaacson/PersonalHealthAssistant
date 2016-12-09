package com.guanyin.sardar.personalhealthassistant.utils;

import android.app.Application;


public class MyApplication extends Application {
    // 整个app都要用到的数据存放在这里
    // 例如 使用sp将用户名和密码存放在这里

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
