package com.guanyin.sardar.pha.alert;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.guanyin.sardar.pha.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.support.v7.appcompat.R.style.Theme_AppCompat_Light_Dialog;


// 日期选择的fragment 使用fragment的args方法进行设置展示的日期

// 当前类中包含从date对象获取年月日和从datePicker中获取年月日转换为date对象的转换方法
public class TimePickerFragment extends DialogFragment {


    int hour;
    int minute;

    private static final String ARG_TIME = "time";
    public static final String EXTRA_TIME = "com.guanyin.sardar.pha.time";

    public static TimePickerFragment newInstance(Date date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TIME, date);

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setArguments(bundle);
        return timePickerFragment;
    }

    private void getTime(Date date) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
        hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        minute = mCalendar.get(Calendar.MINUTE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);

        // 从参数中获取date对象
        Date date = (Date) getArguments().getSerializable(ARG_TIME);
        getTime(date);


        final TimePicker mTimePicker = (TimePicker) view.findViewById(R.id.dialog_time_time_picker);

        mTimePicker.setIs24HourView(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        }
        return new AlertDialog.Builder(getActivity(),Theme_AppCompat_Light_Dialog)
                .setView(view)
                .setTitle(com.guanyin.sardar.pha.R.string.time_picker_title)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_CANCELED, null);
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build
                                        .VERSION_CODES.M) {
                                    int hour = mTimePicker.getHour();
                                    int minute = mTimePicker.getMinute();
                                    Calendar calendar = new GregorianCalendar(0, 0, 0, hour,
                                            minute);
                                    Date date1 = calendar.getTime();
                                    sendResult(Activity.RESULT_OK, date1);
                                }
                            }
                        }
                )
                .create();
    }

    private void sendResult(int resultOk, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultOk, intent);
    }


}
