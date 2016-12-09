package com.guanyin.sardar.personalhealthassistant.utils;

import android.content.Context;
import android.util.Log;


public class Const {

    // 将静态的方法放进来进行调用
    final static boolean debug = true;

    public static void showToast(Context context, String contents) {
        if (debug) {
            MyToast.showToast(context, contents);
        }

    }

    public static void log(String TAG, String message) {
        if (debug) {
            Log.e(TAG, message);
        }
    }
}
