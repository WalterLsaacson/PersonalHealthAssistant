package com.guanyin.sardar.pha.count;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.count.model.DayLabMulti;
import com.guanyin.sardar.pha.status.model.Day;
import com.guanyin.sardar.pha.utils.Const;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

import static android.support.v7.appcompat.R.style.Theme_AppCompat_Light_Dialog;


public class CountFragment extends Fragment implements View.OnClickListener, CompoundButton
        .OnCheckedChangeListener {

    // 過去所有的數值顯示
    TextView tv_total_avg_step;
    TextView tv_total_avg_water;
    TextView tv_total_avg_sleep;

    // 所有的days拿过来
    DayLabMulti dayLabMulti;
    ArrayList<Day> days;

    // 平均值
    int avg_step;
    int avg_water;
    int avg_sleep;

    //
    Button start_date;
    Button end_date;

    final int START_TIME = 0;
    final int END_TIME = 1;

    SharedPreferences mSharedPreferences;
    private long startTime;
    private long endTime;

    // 折线图需要的数据
    private ArrayList<Day> someDays;
    // 第三方折线图
    private LineChartView lineChart;

    private List<PointValue> mPointValues = new ArrayList<>();
    private List<AxisValue> mAxisXValues = new ArrayList<>();

    private final static int FLAG_STEP = 0;
    private final static int FLAG_SLEEP = 1;
    private final static int FLAG_WATER = 2;

    private int flag = FLAG_SLEEP;

    RadioButton rb_sleep;
    RadioButton rb_step;
    RadioButton rb_water;

    public static CountFragment newInstance() {
        Bundle args = new Bundle();
        CountFragment fragment = new CountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {

        dayLabMulti = DayLabMulti.get(getActivity());
        days = dayLabMulti.getDays();
        // 计算总的平均值
        double total_step = 0;
        double total_water = 0;
        double total_sleep = 0;
        Day day;
        for (int i = 0; i < days.size(); i++) {
            day = days.get(i);
            total_sleep += day.getSleep();
            total_water += day.getWater();
            total_step += day.getStep();
            Const.log("CountFragment", day.toString());
        }
        avg_sleep = (int) (total_sleep / (days.size() - 1));
        avg_step = (int) (total_step / (days.size() - 1));
        avg_water = (int) (total_water / (days.size() - 1));

        mSharedPreferences = getActivity().getSharedPreferences("time", Context.MODE_PRIVATE);
        startTime = mSharedPreferences.getLong("start_time", Const.getCurrentMillisecond());
        endTime = mSharedPreferences.getLong("end_time", Const.getCurrentMillisecond());
        // 获取数据
        someDays = new ArrayList<>();
        queryDays();
    }


    private void initView(View view) {
        tv_total_avg_step = (TextView) view.findViewById(R.id.total_avg_step);
        tv_total_avg_step.setText(avg_step + "");
        tv_total_avg_water = (TextView) view.findViewById(R.id.total_avg_water);
        tv_total_avg_water.setText(avg_water + "");
        tv_total_avg_sleep = (TextView) view.findViewById(R.id.total_avg_sleep);
        tv_total_avg_sleep.setText(avg_sleep + "");

        // 按钮设置文本变为17-04-1的形式
        Calendar calendar = Calendar.getInstance();
        start_date = (Button) view.findViewById(R.id.start_date);
        start_date.setOnClickListener(this);
        calendar.setTimeInMillis(startTime);
        int value = Const.getCurrentDateInteger(calendar) - 20000000;
        start_date.setText("" + value / 10000 + "-" +
                value % 10000 / 100 + "-" + value
                % 10000 % 100);
        end_date = (Button) view.findViewById(R.id.end_date);
        end_date.setOnClickListener(this);
        calendar.setTimeInMillis(endTime);
        value = Const.getCurrentDateInteger(calendar) - 20000000;
        end_date.setText("" + value / 10000 + "-" +
                value % 10000 / 100 + "-" + value
                % 10000 % 100);

        // 折线图
        lineChart = (LineChartView) view.findViewById(R.id.line_chart);
        initLineChart();
        rb_sleep = (RadioButton) view.findViewById(R.id.chart_sleep);
        rb_sleep.setOnCheckedChangeListener(this);
        rb_sleep.setChecked(true);
        rb_step = (RadioButton) view.findViewById(R.id.chart_step);
        rb_step.setOnCheckedChangeListener(this);
        rb_water = (RadioButton) view.findViewById(R.id.chart_water);
        rb_water.setOnCheckedChangeListener(this);
    }


    // 初始化
    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE
        // ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.BLACK);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
//        Axis axisY = new Axis();  //Y轴
//        axisY.setName("");//y轴标注
//        axisY.setTextSize(10);//设置字体大小
//        data.setAxisYLeft(axisY);  //Y轴设置在左边
//        data.setAxisYRight(axisY);  //y轴设置在右边


        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /*注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers
         * .com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
//        Viewport v = new Viewport(lineChart.getMaximumViewport());
//        v.left = 0;
//        v.right = 7;
//        lineChart.setCurrentViewport(v);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_date:
                showDatePick("开始时间", START_TIME);
                break;
            case R.id.end_date:
                showDatePick("结束时间", END_TIME);
                break;
        }
    }

    private void queryDays() {
        Const.log("CountFragment--queryDays", "进行查询 ");
        someDays = dayLabMulti.getSomeDays(startTime, endTime);
        for (Day day : someDays
                ) {
            Const.log("CountFragment--queryDays", day.toString());
        }
        refreshChartData();
    }

    private void showDatePick(String title, final int flag) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app
                .AlertDialog.Builder(getActivity(), Theme_AppCompat_Light_Dialog);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.date_dialog, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.dialog_date_picker);
        //TODO 这里的初始化日期之后需要改动
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("initDate",
//                MODE_PRIVATE);
//        long initMilli = sharedPreferences.getLong("initDate", 0);
//        datePicker.setMinDate(initMilli);
        datePicker.setMaxDate(Const.getCurrentMillisecond());
        builder.setTitle(title);
        builder.setView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker
                        .getDayOfMonth());
                updateDate(calendar, flag);
            }
        });
        builder.show();
    }

    private void updateDate(Calendar calendar, int flag) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        int value = Const.getCurrentDateInteger(calendar) - 20000000;
        // 170401
        switch (flag) {
            case START_TIME:
                start_date.setText("" + value / 10000 + "-" +
                        value % 10000 / 100 + "-" + value
                        % 10000 % 100);
                startTime = Const.getCurrentMillisecond(calendar);
                break;
            case END_TIME:
                end_date.setText("" + value / 10000 + "-" +
                        value % 10000 / 100 + "-" + value
                        % 10000 % 100);
                endTime = Const.getCurrentMillisecond(calendar);
                break;
        }
        // startTime 应该是小于endTime的
        if (startTime > endTime) {
            //do nothing
            Const.showToast(getActivity(), "请选择合适的开始/结束时间!");
        } else {
            editor.putLong("start_time", startTime);
            editor.putLong("end_time", endTime);
            editor.apply();
            // 进行查询 画图
            queryDays();
            initLineChart();
        }
    }

    private void refreshChartData() {
        mAxisXValues.clear();
        mPointValues.clear();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) * 10000;
        int value;
        for (int i = 0; i < someDays.size(); i++) {
            value = someDays.get(i).getDate() - year;
            mAxisXValues.add(new AxisValue(i).setLabel("" + value / 100 + "-" + value % 100));
            switch (flag) {
                case FLAG_SLEEP:
                    mPointValues.add(new PointValue(i, someDays.get(i).getSleep()));
                    break;
                case FLAG_STEP:
                    mPointValues.add(new PointValue(i, someDays.get(i).getStep()));
                    break;
                case FLAG_WATER:
                    mPointValues.add(new PointValue(i, someDays.get(i).getWater()));
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        if (isChecked) {
            switch (v.getId()) {
                case R.id.chart_sleep:
                    flag = FLAG_SLEEP;
                    break;
                case R.id.chart_step:
                    flag = FLAG_STEP;
                    break;
                case R.id.chart_water:
                    flag = FLAG_WATER;
                    break;
            }
            refreshChartData();
            initLineChart();
        }
    }

}
