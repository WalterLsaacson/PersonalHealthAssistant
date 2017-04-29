package com.guanyin.sardar.pha.status;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.mine.model.IndInfoLab;
import com.guanyin.sardar.pha.mine.model.IndividualInfo;
import com.guanyin.sardar.pha.status.model.Day;
import com.guanyin.sardar.pha.status.model.DayLab;
import com.guanyin.sardar.pha.utils.Const;
import com.guanyin.sardar.pha.utils.MyApplication;

import java.util.Timer;
import java.util.TimerTask;

import static android.support.v7.appcompat.R.style.Theme_AppCompat_Light_Dialog;
import static android.text.InputType.TYPE_CLASS_NUMBER;


public class StatusFragment extends Fragment implements View.OnClickListener {

    // 要显示的任务
    TextView tv_water;
    TextView tv_sleep;
    TextView tv_step;
    TextView tv_mark;

    // 添加任务进度
    Button add_water;
    Button add_step;
    Button add_rest;

    // 任务完成图标
    ImageView iv_rest;
    ImageView iv_step;
    ImageView iv_water;

    // 测试结果
    TextView tv_tips;

    DayLab dayLab;
    Day day;

    IndInfoLab infoLab;
    IndividualInfo individualInfo;

    MyApplication mMyApplication;

    //
    private static final int POSITION_SLEEP = 0;
    private static final int POSITION_STEP = 1;
    private static final int POSITION_WATER = 2;

    String[] titles = new String[]{"休息时长(分钟)", "运动时长(分钟)", "饮水量(ML)"};

    public static StatusFragment newInstance() {
        Bundle args = new Bundle();
        StatusFragment fragment = new StatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        dayLab = DayLab.get(getActivity());
        day = dayLab.getDay();

        infoLab = IndInfoLab.get(getActivity());
        individualInfo = infoLab.getIndividualInfo();

        mMyApplication = MyApplication.getInstance();
    }

    private void initView(View view) {

        add_water = (Button) view.findViewById(R.id.add_water);
        add_water.setOnClickListener(this);
        add_step = (Button) view.findViewById(R.id.add_step_num);
        add_step.setOnClickListener(this);
        add_rest = (Button) view.findViewById(R.id.add_sleep);
        add_rest.setOnClickListener(this);

        iv_rest = (ImageView) view.findViewById(R.id.iv_rest);
        iv_step = (ImageView) view.findViewById(R.id.iv_step);
        iv_water = (ImageView) view.findViewById(R.id.iv_water);

        tv_sleep = (TextView) view.findViewById(R.id.today_sleep_hour);
        int countMin = individualInfo.getSleepTime() - day.getSleep();
        setSleepView(countMin);

        tv_step = (TextView) view.findViewById(R.id.today_step_num);
        int step_time = individualInfo.getRunDuration() - day.getStep();
        setStepView(step_time);

        tv_water = (TextView) view.findViewById(R.id.today_water);
        int waterIntake = individualInfo.getWaterIntake() - day.getWater();
        setWaterView(waterIntake);


        tv_mark = (TextView) view.findViewById(R.id.my_health_mark);
        tv_mark.setText(individualInfo.getHealthMark() + "");
        setColor();

        tv_tips = (TextView) view.findViewById(R.id.tips);
        tv_tips.setText(mMyApplication.sp.getString("tips", "请完善个人信息。"));

    }

    // 饮水量的设置
    private void setWaterView(int waterIntake) {
        if (waterIntake <= 0) {
            tv_water.setTextSize(15);
            tv_water.setTextColor(ContextCompat.getColor(getActivity(), R.color.eighth));
            tv_water.setText("已完成今日的饮水计划");
            add_water.setVisibility(View.GONE);
            iv_water.setVisibility(View.VISIBLE);
        } else {
            tv_water.setText(waterIntake + "ML");
        }
    }

    // 运动时间的设置
    private void setStepView(int step_time) {
        int hour, min;
        if (step_time <= 0) {
            tv_step.setTextSize(15);
            tv_step.setTextColor(ContextCompat.getColor(getActivity(), R.color.eighth));
            tv_step.setText("已完成今日的运动计划");
            add_step.setVisibility(View.GONE);
            iv_step.setVisibility(View.VISIBLE);
        } else {
            hour = step_time / 60;
            min = step_time % 60;
            if (min == 0) {
                tv_step.setText(hour + "小时");
            } else if (hour == 0) {
                tv_sleep.setText(min + "分钟");
            } else {
                tv_step.setText(hour + "小时" + min + "分钟");
            }
        }
    }

    // 睡眠时间的设置
    private void setSleepView(int countMin) {
        int hour, min;
        if (countMin <= 0) {
            tv_sleep.setTextSize(15);
            tv_sleep.setTextColor(ContextCompat.getColor(getActivity(), R.color.eighth));
            tv_sleep.setText("已完成今日的睡眠计划");
            add_rest.setVisibility(View.GONE);
            iv_rest.setVisibility(View.VISIBLE);
        } else {
            hour = countMin / 60;
            min = countMin % 60;
            if (min == 0) {
                tv_sleep.setText(hour + "小时");
            } else if (hour == 0) {
                tv_sleep.setText(min + "分钟");
            } else {
                tv_sleep.setText(hour + "小时" + min + "分钟");
            }
        }
    }

    // 不同健康分数对应不同颜色
    private void setColor() {
        int mark = individualInfo.getHealthMark();
        if (mark >= 95) {
            tv_mark.setTextColor(ContextCompat.getColor(getActivity(), R.color.eighth));
        } else if (mark >= 90) {
            tv_mark.setTextColor(ContextCompat.getColor(getActivity(), R.color.seventh));
        } else if (mark >= 85) {
            tv_mark.setTextColor(ContextCompat.getColor(getActivity(), R.color.sixth));
        } else if (mark >= 80) {
            tv_mark.setTextColor(ContextCompat.getColor(getActivity(), R.color.fifth));
        } else if (mark >= 75) {
            tv_mark.setTextColor(ContextCompat.getColor(getActivity(), R.color.first));
        } else if (mark >= 70) {
            tv_mark.setTextColor(ContextCompat.getColor(getActivity(), R.color.second));
        } else if (mark >= 65) {
            tv_mark.setTextColor(ContextCompat.getColor(getActivity(), R.color.third));
        } else if (mark >= 60) {
            tv_mark.setTextColor(ContextCompat.getColor(getActivity(), R.color.forth));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_sleep:
                enterDialogSupportV7(POSITION_SLEEP);
                break;
            case R.id.add_water:
                enterDialogSupportV7(POSITION_WATER);
                break;
            case R.id.add_step_num:
                enterDialogSupportV7(POSITION_STEP);
                break;
        }
    }

    private void enterDialogSupportV7(final int positionDialog) {
        android.support.v7.app.AlertDialog.Builder builderV7 = new android.support.v7.app
                .AlertDialog.Builder(getActivity(), Theme_AppCompat_Light_Dialog);
        builderV7.setTitle("添加已完成的" + titles[positionDialog]);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input, null);
        final EditText enter = (EditText) view.findViewById(R.id.dialog_input);
        enter.setInputType(TYPE_CLASS_NUMBER);
        enter.setMaxLines(4);
        builderV7.setView(view);
        builderV7.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newEnter = enter.getText().toString();
                if (!newEnter.equals("")) {
                    updateDayInfoAndView(positionDialog, Const.stringToInteger(newEnter));
                }
            }
        });
        builderV7.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderV7.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //设置可获得焦点
                enter.setFocusable(true);
                enter.setFocusableInTouchMode(true);
                //请求获得焦点
                enter.requestFocus();
                //调用系统输入法
                InputMethodManager inputManager = (InputMethodManager) enter
                        .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(enter, 0);
            }
        }, 100);
    }


    private void updateDayInfoAndView(int positionDialog, int content) {
        switch (positionDialog) {
            case POSITION_SLEEP:
                day.setSleep(day.getSleep() + content);
                int countMin = individualInfo.getSleepTime() - day.getSleep();
                setSleepView(countMin);
                break;
            case POSITION_STEP:
                day.setStep(day.getStep() + content);
                int step_time = individualInfo.getRunDuration() - day.getStep();
                setStepView(step_time);
                break;
            case POSITION_WATER:
                day.setWater(day.getWater() + content);
                int waterIntake = individualInfo.getWaterIntake() - day.getWater();
                setWaterView(waterIntake);
                break;
        }
    }

    @Override
    public void onStop() {
        dayLab.updateDay(day);
        super.onStop();
    }
}
