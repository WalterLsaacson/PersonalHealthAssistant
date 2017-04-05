package com.guanyin.sardar.pha;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanyin.sardar.pha.mine.EnterInfoActivity;
import com.guanyin.sardar.pha.utils.MyApplication;

import java.util.Timer;
import java.util.TimerTask;


public class FlashFragment extends Fragment {

    // 定时跳过本activity
    Timer mTimer;
    TimerTask mTimerTask;

    Intent intent;

    TextView timeTextView;
    MyApplication mApplication;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View view = inflater.inflate(com.guanyin.sardar.pha.R.layout.fragment_flash, container,
                false);
        // 获取application的实例
        if (mApplication == null) {
            mApplication = MyApplication.getInstance();
        }
        // 设置文字递减
        mCountDownTimer.start();
        // 设置点击跳过当前activity
        timeTextView = (TextView) view.findViewById(com.guanyin.sardar.pha.R.id.timeTextView);
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAnotherActivity();
                mTimer.cancel();
                getActivity().finish();
            }
        });
        // 设置定时器 自动跳过当前activity
        mTimer = new Timer();

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                toAnotherActivity();
                getActivity().finish();
            }
        };
        mTimer.schedule(mTimerTask, 3000);
        return view;
    }

    // TODO 添加服务器之后 可以进行登录状态的判断 然后跳转不同的activity
    // 录入用户数据或者已经录入现在直接进入主界面
    public void toAnotherActivity() {
        if (MyApplication.initApp++ == 1) {
            intent = EnterInfoActivity.newIntent(getActivity());
            startActivity(intent);
        } else {
            intent = FunctionActivity.newIntent(getActivity());
            startActivity(intent);
        }
    }

    // 文本倒计时动态变化的实现
    private CountDownTimer mCountDownTimer = new CountDownTimer(4000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String timeCount = (millisUntilFinished / 1000) + "s";
            timeTextView.setText(timeCount);
        }

        @Override
        public void onFinish() {
            timeTextView.setVisibility(View.INVISIBLE);
        }
    };

    // 封装获取当前fragment的方法
    public static FlashFragment newInstance() {

        Bundle args = new Bundle();

        FlashFragment fragment = new FlashFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
