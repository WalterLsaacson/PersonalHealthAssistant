package com.guanyin.sardar.pha.alert.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.alert.ClockEditActivity;

import static com.guanyin.sardar.pha.alert.ClockEditActivity.EXTRA_CLOCK_EDIT_ID;

public class AlarmReceiveActivity extends AppCompatActivity {

    TextView tv_stop;
    public static Intent newIntent(Context packageContext, Intent lastIntent) {
        Intent intent = new Intent(packageContext, ClockEditActivity.class);
        intent.putExtras(lastIntent);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_receive);
        tv_stop = (TextView) findViewById(R.id.alarm_stop);
        tv_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
