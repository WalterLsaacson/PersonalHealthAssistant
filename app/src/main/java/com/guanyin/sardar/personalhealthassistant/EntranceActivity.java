package com.guanyin.sardar.personalhealthassistant;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.guanyin.sardar.personalhealthassistant.utils.MyApplication;

import java.util.Timer;
import java.util.TimerTask;

public class EntranceActivity extends AppCompatActivity {

    Intent intent;
    // 定时跳过本activity
    Timer mTimer;
    TimerTask mTimerTask;

    TextView timeTextView;
    MyApplication mApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        // 获取application的实例
        if (mApplication == null) {
            mApplication = MyApplication.getInstance();
        }

// 设置文字递减
        mCountDownTimer.start();
        // 设置点击跳过当前activity
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAnotherActivity();
                mTimer.cancel();
                finish();
            }
        });
        // 设置定时器 自动跳过当前activity
        mTimer = new Timer();

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                toAnotherActivity();
                finish();
            }
        };
        mTimer.schedule(mTimerTask, 5000);
    }

    // TODO 添加服务器之后 可以进行登录状态的判断 然后跳转不同的activity

    private void toAnotherActivity() {
        intent = new Intent(EntranceActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private CountDownTimer mCountDownTimer = new CountDownTimer(6000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timeTextView.setText((millisUntilFinished / 1000) + "s");
        }

        @Override
        public void onFinish() {
            timeTextView.setVisibility(View.INVISIBLE);
        }
    };

}
