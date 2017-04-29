package com.guanyin.sardar.pha.alert.receiver;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.guanyin.sardar.pha.R;


public class AlarmReceiver extends BroadcastReceiver {

    //    private Clock mClock;
    public static final String EXTRA_RECEIVER_TITLE = "clockTitle";
    public static final String EXTRA_RECEIVER_ID = "clockId";
    public static final String EXTRA_RECEIVER_TIME = "clockTime";
    public static final String ALARM_RECEIVER_ACTION = "alarm_receiver_action";

    public static Intent newReceiver(Context packageContext, String mClockId,
                                     String mClockTitle, String mClockTime) {
        Intent intent = new Intent(packageContext, AlarmReceiver.class);
        intent.setAction(ALARM_RECEIVER_ACTION);
        intent.putExtra(EXTRA_RECEIVER_ID, mClockId);
        intent.putExtra(EXTRA_RECEIVER_TITLE, mClockTitle);
        intent.putExtra(EXTRA_RECEIVER_TIME, mClockTime);
        return intent;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.logo);
        String clockTitle = intent.getStringExtra(EXTRA_RECEIVER_TITLE);
        builder.setContentTitle(clockTitle);
        builder.setContentText("睡觉时间到了！");
        builder.setVisibility(Notification.VISIBILITY_PRIVATE);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context
                .NOTIFICATION_SERVICE);
        manager.notify(0, notification);

//        Const.log("AlarmReceiver", clockTitle);
//        Const.showToast(context, "收到广播");
//        Intent alarmReceiveActivityIntent = AlarmReceiveActivity.newIntent(context, intent);
//        alarmReceiveActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
//                .FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//        context.startActivity(alarmReceiveActivityIntent);

        wl.release();
    }

}
