package com.guanyin.sardar.pha.alert.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.guanyin.sardar.pha.model.Clock;
import com.guanyin.sardar.pha.model.ClockLab;
import com.guanyin.sardar.pha.alert.service.MusicService;
import com.guanyin.sardar.pha.utils.Const;

import java.util.List;


public class AlarmReceiver extends BroadcastReceiver {

//    private Clock mClock;
    public static final String EXTRA_RECEIVER_TITLE = "clockTitle";
    public static final String EXTRA_RECEIVER_ID = "clockId";
    public static final String ALARM_RECEIVER_ACTION = "alarm_receiver_action";
//    private ClockLab mClockLab;
    private String mClockTitle;

    public static Intent newReceiver(Context packageContext, String mClockId, String mClockTitle) {
        Intent intent = new Intent(packageContext, AlarmReceiver.class);
        intent.setAction(ALARM_RECEIVER_ACTION);
        intent.putExtra(EXTRA_RECEIVER_ID, mClockId);
        intent.putExtra(EXTRA_RECEIVER_TITLE, mClockTitle);
        return intent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        gotClock(context, intent);
        mClockTitle = intent.getStringExtra(EXTRA_RECEIVER_TITLE);
        Const.log("AlarmReceiver", mClockTitle);
        Const.showToast(context, "收到广播");
        Intent alarmReceiveActivityIntent = AlarmReceiveActivity.newIntent(context,intent);
        context.startActivity(alarmReceiveActivityIntent);
    }

    //     根据获取的参数，得知当前编辑的是哪一个clock条目
//    private void gotClock(Context context, Intent intent) {
//        mClockLab = ClockLab.get(context);
//        String id = intent.getStringExtra(EXTRA_RECEIVER_ID);
//        List<Clock> clocks = mClockLab.getClocks();
//        for (int i = 0; i < clocks.size(); i++) {
//            if (id.equals(clocks.get(i).getId())) {
//                mClock = clocks.get(i);
//                break;
//            }
//        }
//    }

}
