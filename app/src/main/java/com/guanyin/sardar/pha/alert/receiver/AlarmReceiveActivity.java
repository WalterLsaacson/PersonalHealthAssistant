package com.guanyin.sardar.pha.alert.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.alert.service.MusicService;

import static com.guanyin.sardar.pha.alert.receiver.AlarmReceiver.EXTRA_RECEIVER_TIME;
import static com.guanyin.sardar.pha.alert.receiver.AlarmReceiver.EXTRA_RECEIVER_TITLE;

public class AlarmReceiveActivity extends AppCompatActivity {

    Intent intent;
    TextView tv_stop;
    TextView tv_time;
    TextView tv_title;

    public static Intent newIntent(Context packageContext, Intent lastIntent) {
        Intent intent = new Intent(packageContext, AlarmReceiveActivity.class);
        intent.putExtras(lastIntent);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_receive);
        intent = MusicService.newIntent(this);
        startService(intent);
        // title
        tv_title = (TextView) findViewById(R.id.alarm_title);
        String mClockTitle = intent.getStringExtra(EXTRA_RECEIVER_TITLE);
        tv_title.setText(mClockTitle);
        // time
        tv_time = (TextView) findViewById(R.id.alarm_time);
        String mClockTime = intent.getStringExtra(EXTRA_RECEIVER_TIME);
        tv_time.setText(mClockTime);
        tv_stop = (TextView) findViewById(R.id.alarm_stop);
        tv_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(intent);
    }
}
