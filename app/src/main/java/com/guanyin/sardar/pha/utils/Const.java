package com.guanyin.sardar.pha.utils;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;


public class Const {

    public static void showToast(Context context, String contents) {
        MyToast.showToast(context, contents);
    }

    public static int stringToInteger(String string) {
        return Integer.parseInt(string);
    }

    public static void log(String TAG, String message) {
        boolean debug = true;
        if (debug) {
            Log.e(TAG, message);
        }
    }

    public static int getCurrentDateInteger() {
        Calendar calender = Calendar.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calender.get(Calendar.YEAR));
        int month = calender.get(Calendar.MONTH) + 1;
        int day = calender.get(Calendar.DAY_OF_MONTH);
        stringBuilder.append(month > 10 ? month : "0" + month);
        stringBuilder.append(day > 10 ? day : "0" + day);
        return stringToInteger(stringBuilder.toString());
    }

    public static int getCurrentDateInteger(Calendar calendar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        stringBuilder.append(month > 10 ? month : "0" + month);
        stringBuilder.append(day > 10 ? day : "0" + day);
        return stringToInteger(stringBuilder.toString());
    }

    public static long getCurrentMillisecond() {
        Calendar calender = Calendar.getInstance();
        return calender.getTimeInMillis();
    }

    public static long getCurrentMillisecond(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

}
