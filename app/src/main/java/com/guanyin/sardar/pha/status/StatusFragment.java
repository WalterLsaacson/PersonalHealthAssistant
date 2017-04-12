package com.guanyin.sardar.pha.status;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.mine.model.IndInfoLab;
import com.guanyin.sardar.pha.mine.model.IndividualInfo;
import com.guanyin.sardar.pha.status.model.Day;
import com.guanyin.sardar.pha.status.model.DayLab;


public class StatusFragment extends Fragment {

    TextView tv_water;
    TextView tv_sleep;
    TextView tv_step;
    TextView tv_mark;

    DayLab dayLab;
    Day day;

    IndInfoLab infoLab;
    IndividualInfo individualInfo;

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
    }

    private void initView(View view) {
        tv_sleep = (TextView) view.findViewById(R.id.today_sleep_hour);
        tv_sleep.setText(individualInfo.getSleepTime());
        tv_step = (TextView) view.findViewById(R.id.today_step_num);
        tv_step.setText(individualInfo.getRunDuration());
        tv_water = (TextView) view.findViewById(R.id.today_water);
        tv_water.setText(individualInfo.getWaterIntake());
        tv_mark = (TextView) view.findViewById(R.id.my_health_mark);
        tv_mark.setText(individualInfo.getHealthMark());
    }

    @Override
    public void onStop() {
        // TODO 问题来了，每天都需要创建一条条目
        dayLab.updateDay(day);
        super.onStop();
    }
}
