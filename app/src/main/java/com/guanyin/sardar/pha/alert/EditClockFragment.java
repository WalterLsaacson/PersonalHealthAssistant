package com.guanyin.sardar.pha.alert;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.alert.model.Clock;
import com.guanyin.sardar.pha.alert.model.ClockLab;
import com.guanyin.sardar.pha.alert.receiver.AlarmReceiver;
import com.guanyin.sardar.pha.utils.Const;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.support.v7.appcompat.R.style.Theme_AppCompat_Light_Dialog;


public class EditClockFragment extends Fragment {

    private static final String ARG_CLOCK_ID = "clock_id";

    // 调用时间选择器时需要的常量
    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_TIME = 1;

    ClockLab mClockLab;
    private Clock mClock;
    // 记录修改前的打开状态
    boolean wasOpened;

    // 界面的控件元素
    ImageView mIcon;
    TextView mTitle;
    CheckBox cb_opened;
    TextView tv_opened;
    Button mTime;
    Button mMusic;

    // 顶部导航
    ImageView action_bar_back;
    ImageView action_bar_done;

    // 根据clock的id属性设置相应的图片
    int[] images;

    public static EditClockFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_CLOCK_ID, id);
        EditClockFragment fragment = new EditClockFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_clock, container, false);
        gotClock();
        images = new int[]{R.drawable.tooth, R.drawable.longtimeseat, R.drawable.bloodpressure,
                R.drawable.sleep, R.drawable.weight, R.drawable.medicine,};
        // 根据相应的clock条目进行布局的更新
        mIcon = (ImageView) view.findViewById(R.id.edit_clock_icon);
        mIcon.setBackgroundResource(images[Const.stringToInteger(mClock.getId())]);
        mTitle = (TextView) view.findViewById(R.id.edit_clock_title);
        mTitle.setText(mClock.getTitle());

        cb_opened = (CheckBox) view.findViewById(R.id.edit_clock_opened_cb);
        cb_opened.setChecked(mClock.isOpen());
        tv_opened = (TextView) view.findViewById(R.id.edit_clock_opened_text);
        tv_opened.setText(mClock.isOpen() ? "开启" : "未开启");
        cb_opened.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tv_opened.setText(isChecked ? "开启" : "未开启");
                mClock.setOpen(isChecked);
            }
        });

        mTime = (Button) view.findViewById(R.id.edit_clock_time);
        mTime.setText(mClock.getDate());
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                // 根据得到的string信息转换为date对象
                String[] time = mClock.getDate().split(":");
                Calendar calendar = Calendar.getInstance();
                calendar.set(0, 0, 0, Const.stringToInteger(time[0]), Const.stringToInteger
                        (time[1]));
                TimePickerFragment timeDialog = TimePickerFragment.newInstance(calendar.getTime());
                timeDialog.setStyle(Theme_AppCompat_Light_Dialog, 0);
                timeDialog.setTargetFragment(EditClockFragment.this, REQUEST_TIME);
                timeDialog.show(manager, DIALOG_TIME);
            }
        });


        mMusic = (Button) view.findViewById(R.id.edit_clock_music);
        mMusic.setText(mClock.getMusic());


        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionbar = activity.getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }


        action_bar_back = (ImageView) view.findViewById(R.id.action_bar_return);
        action_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        action_bar_done = (ImageView) view.findViewById(R.id.action_bar_done);
        action_bar_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataBase();
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQUEST_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            // 从传过来的数据中拿到小时、分钟的信息
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTime(date);
            int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = mCalendar.get(Calendar.MINUTE);
            // 第三步：合并数据，并保存在对象中
            StringBuilder sb = new StringBuilder();
            if (hour < 10) {
                sb.append("0").append(hour).append(":");
            } else {
                sb.append(hour).append(":");
            }
            if (minute < 10) {
                sb.append("0").append(minute);
            } else {
                sb.append(minute);
            }
            mClock.setDate(sb.toString());
            // 更新视图
            mTime.setText(mClock.getDate());
        }
    }


    // 更新当前页面进行的修改

    private void updateDataBase() {
        // 更新数据库的数据
        mClockLab.updateClock(mClock);
        // 添加闹钟到系统服务中
        if (wasOpened) {
            // 之前就是开启
            // 1.注销之前的闹钟
            Intent intent = AlarmReceiver.newReceiver(getActivity(), mClock.getId(),
                    mClock.getTitle(), mClock.getDate());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,
                    intent, 0);
            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context
                    .ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
            if (mClock.isOpen()) {
                // 离开时仍是开启 则注册
                registerAlarm();
            } else {
                Const.showToast(getActivity(), mClock.getTitle() + "取消");
            }
        } else {
            // 之前是未开启
            if (!mClock.isOpen()) {
                // 离开时仍未开启
                Const.showToast(getActivity(), mClock.getTitle() + "未开启");
            } else {
                // 离开时开启 则注册
                registerAlarm();
            }
        }
    }

    // 注册
    private void registerAlarm() {
        Intent intent = AlarmReceiver.newReceiver(getActivity(), mClock.getId(), mClock
                .getTitle(), mClock.getDate());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,
                intent, 0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context
                .ALARM_SERVICE);
        long firstOffset = getFirstOffset();
        alarmManager.set(AlarmManager.RTC_WAKEUP, firstOffset, pendingIntent);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                firstOffset + (System.currentTimeMillis()),
//                24 * 60 * 60 * 1000,
//                pendingIntent);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                System.currentTimeMillis(),
//                2 * 60 * 1000,
//                pendingIntent);
        Const.showToast(getActivity(), format(firstOffset));
    }

    private long getFirstOffset() {
        // 获取第一次的时间间隔
        String[] time = mClock.getDate().split(":");
        long firstTimeOffset;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long currentTimeMills = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, Const.stringToInteger(time[0]));
        calendar.set(Calendar.MINUTE, Const.stringToInteger(time[1]));
        firstTimeOffset = (currentTimeMills < calendar.getTimeInMillis())
                ? calendar.getTimeInMillis() - currentTimeMills
                : (24 * 60 * 60 * 1000) - (currentTimeMills - calendar.getTimeInMillis());
        return firstTimeOffset;
    }

    // 装换下一次间隔的毫秒数为小时、分钟的叙述
    private String format(long firstTimeOffset) {
        int hour;
        int minutes;
        hour = (int) ((firstTimeOffset / 1000) / 60 / 60);
        minutes = (int) ((firstTimeOffset / 1000) / 60 % 60);
        StringBuilder sb = new StringBuilder();
        sb.append("下次")
                .append(mClock.getTitle())
                .append("将在");
        if (hour == 24) {
            sb.append("一天之后提醒");
            return sb.toString();
        }
        if (hour != 0) {
            sb.append(hour)
                    .append("小时");
        }
        if (minutes != 0) {
            sb.append(minutes)
                    .append("分钟");
        }
        sb.append("之后");
        return sb.toString();
    }

    // 根据获取的参数，得知当前编辑的是哪一个clock条目
    private void gotClock() {
        mClockLab = ClockLab.get(getActivity());
        List<Clock> clocks = mClockLab.getClocks();
        for (int i = 0; i < clocks.size(); i++) {
            if (getArguments().get(ARG_CLOCK_ID).equals(clocks.get(i).getId())) {
                mClock = clocks.get(i);
                break;
            }
        }
        wasOpened = mClock.isOpen();
    }
}
