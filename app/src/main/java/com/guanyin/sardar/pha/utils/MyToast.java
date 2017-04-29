package com.guanyin.sardar.pha.utils;

import android.content.Context;
import android.widget.Toast;


public class MyToast {
    public static void showToast(Context context, String message) {
        /* custom my toast
        * TODO add view*/

        // how to do
        // 1.加载自定义的视图
        //View view  = LayoutInflater.from(context).inflate(R.layout.activity_fragment,null);
        // 2.获取视图上的文本域
//        TextView textView = view.findViewById(R.id.);
//        textView.setText(message);
        // 3.创建系统的toast并把自己的视图加载进去
//        Toast toast = new Toast(context);
        // 显示时间
//        toast.setDuration(Toast.LENGTH_SHORT);
        // 显示位置
//        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 120);
        // 加载自己的视图并显示
//        toast.setView(view);
//        toast.show();

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
