package com.guanyin.sardar.personalhealthassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.guanyin.sardar.personalhealthassistant.model.Clock;
import com.guanyin.sardar.personalhealthassistant.model.ClockLab;
import com.guanyin.sardar.personalhealthassistant.service.MusicService;
import com.guanyin.sardar.personalhealthassistant.utils.Const;

import java.util.List;


public class AlarmReceiver extends BroadcastReceiver {

    ClockLab mClockLab;
    private Clock mClock;
    Intent mIntent;
    public static final String EXTRA_RECEIVER_ID = "clockId";

    public static Intent newReceiver(Context packageContext, String clockId) {
        Intent intent = new Intent(packageContext, AlarmReceiver.class);
        intent.putExtra(EXTRA_RECEIVER_ID, clockId);
        return intent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        gotClock(context, intent);
        Const.log("AlarmReceiver", mClock.getTitle());
        switch (mClock.getTitle()) {
            case "护牙提醒":
                mIntent = new Intent(context, MusicService.class);
                context.startService(mIntent);
                break;
            case "运动提醒":
            case "量血压提醒":
            case "睡眠提醒":
            case "测体重提醒":
            case "服药提醒":

        }

    }

    // 根据获取的参数，得知当前编辑的是哪一个clock条目
    private void gotClock(Context context, Intent intent) {
        mClockLab = ClockLab.get(context);
        String id = intent.getStringExtra(EXTRA_RECEIVER_ID);
        List<Clock> clocks = mClockLab.getClocks();
        for (int i = 0; i < clocks.size(); i++) {
            if (id.equals(clocks.get(i).getId())) {
                mClock = clocks.get(i);
                break;
            }
        }
    }

}
