package com.guanyin.sardar.pha.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.mine.model.IndInfoLab;
import com.guanyin.sardar.pha.mine.model.IndividualInfo;
import com.guanyin.sardar.pha.utils.Const;
import com.guanyin.sardar.pha.utils.MyApplication;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.support.v7.appcompat.R.style.Theme_AppCompat_Light_Dialog;
import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;


public class MineFragmentAdd extends Fragment {

    private static IndividualInfo sIndividualInfo;
    private IndInfoLab mIndInfoLab;

    MyAdapter mDetailAdapter;
    MyAdapter mFoundationAdapter;

    ArrayList<String> foundation_names;
    ArrayList<String> foundation_content;

    ArrayList<String> detail_names;
    ArrayList<String> detail_content;
    Calendar mCalendar = Calendar.getInstance();
    int currentYear = mCalendar.get(Calendar.YEAR);

    public static MineFragmentAdd newInstance() {

        Bundle args = new Bundle();

        MineFragmentAdd fragment = new MineFragmentAdd();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_add, container, false);
        initData();
        initView(view);


        return view;
    }

    private void initData() {
        mIndInfoLab = IndInfoLab.get(getActivity());
        sIndividualInfo = mIndInfoLab.getIndividualInfo();
        Const.log("MineFragment", sIndividualInfo.toString());
        // 基本信息
        foundation_names = new ArrayList<>();
        foundation_content = new ArrayList<>();
        foundation_names.add("昵称");
        foundation_content.add(sIndividualInfo.getPetName());
        foundation_names.add("性别");
        foundation_content.add(sIndividualInfo.getSex());
        foundation_names.add("出生年份");
        int age = Integer.parseInt(sIndividualInfo.getAge());
        foundation_content.add(currentYear - age + "");
        foundation_names.add("身高(CM)");
        foundation_content.add(sIndividualInfo.getHeight());
        foundation_names.add("体重(KG)");
        foundation_content.add(sIndividualInfo.getWeight());
        // 详细信息
        detail_names = new ArrayList<>();
        detail_content = new ArrayList<>();
        detail_names.add("腰围(CM)");
        detail_content.add(sIndividualInfo.getWaistLine());
        detail_names.add("脉搏(次/min)");
        detail_content.add(sIndividualInfo.getPulse());
        detail_names.add("血压(收缩压mmHg)");
        detail_content.add(sIndividualInfo.getSystolicPressure());
        detail_names.add("血压(舒张压mmHg)");
        detail_content.add(sIndividualInfo.getDiastolicPressure());
        detail_names.add("血糖(mmol/L)");
        detail_content.add(sIndividualInfo.getBloodSugar());
        detail_names.add("血脂(TC mmol/L)");
        detail_content.add(sIndividualInfo.getTC());
    }

    private void initView(View view) {
        final RecyclerView foundation_recyclerView = (RecyclerView) view.findViewById(R.id
                .foundation_info_recycle_view);
        foundation_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        foundation_recyclerView.setHasFixedSize(true);
        mFoundationAdapter = new MyAdapter(foundation_names, foundation_content);
        foundation_recyclerView.setAdapter(mFoundationAdapter);
        mFoundationAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        inputDialog(position, "更新" + foundation_names.get(position));
                        break;
                    case 1:
                        sexSelectDialog(position, "更新" + foundation_names.get(position));
                        break;
                    case 2:
                        int initYear = Integer.parseInt(foundation_content.get(position));
                        int initYPosition = 0;
                        List<String> years = new ArrayList<>();
                        int currentYear1 = currentYear - 18;
                        for (int i = 1940; i <= currentYear1; i++) {
                            years.add(i + "");
                            if (i == initYear) {
                                initYPosition = years.size() - 1;
                            }
                        }
                        wheelViewDialog(position, "更新" + foundation_names.get(position), years,
                                initYPosition);
                        break;
                    case 3:
                        int initHeight = Integer.parseInt(sIndividualInfo.getHeight());
                        int initHPosition = 0;
                        List<String> height = new ArrayList<>();
                        for (int i = 150; i <= 210; i++) {
                            height.add(i + "");
                            if (i == initHeight) {
                                initHPosition = height.size() - 1;
                            }
                        }
                        wheelViewDialog(position, "更新" + foundation_names.get(position), height,
                                initHPosition);
                        break;
                    case 4:
                        int initWeight = Integer.parseInt(sIndividualInfo.getWeight());
                        int initWPosition = 0;
                        List<String> weight = new ArrayList<>();
                        for (int i = 40; i <= 150; i++) {
                            weight.add(i + "");
                            if (i == initWeight) {
                                initWPosition = weight.size() - 1;
                            }
                        }
                        wheelViewDialog(position, "更新" + foundation_names.get(position), weight,
                                initWPosition);
                        break;
                }
            }
        });

        RecyclerView detail_recyclerView = (RecyclerView) view.findViewById(R.id
                .detail_info_recycle_view);
        // disable recycleView scrolling
//        CustomLayoutManager customLayoutManager = new CustomLayoutManager(getActivity());
//        customLayoutManager.setEnableScrolled(false);
        detail_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        detail_recyclerView.setHasFixedSize(true);
        mDetailAdapter = new MyAdapter(detail_names, detail_content);
        detail_recyclerView.setAdapter(mDetailAdapter);
        mDetailAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                enterDialog(position);
                enterDialogSupportV7(position);
            }
        });
    }

//    private void enterDialog(final int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
//                Theme_DeviceDefault_Light_Dialog_Alert);
//        builder.setTitle("更新" + detail_names.get(position));
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input, null);
//        final EditText enter = (EditText) view.findViewById(R.id.dialog_input);
//        enter.setInputType(TYPE_CLASS_NUMBER);
//        builder.setView(view);
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String newEnter = enter.getText().toString();
//                if (!newEnter.equals("")) {
//                    updateDetailInfo(position, newEnter);
//                    detail_content.set(position, newEnter);
//                    mDetailAdapter.notifyItemChanged(position);
//                }
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        builder.show();
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //设置可获得焦点
//                enter.setFocusable(true);
//                enter.setFocusableInTouchMode(true);
//                //请求获得焦点
//                enter.requestFocus();
//                //调用系统输入法
//                InputMethodManager inputManager = (InputMethodManager) enter
//                        .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                inputManager.showSoftInput(enter, 0);
//            }
//        }, 100);
//    }


    private void inputDialog(final int position, final String petName) {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
//                Theme_DeviceDefault_Light_Dialog_Alert);
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app
                .AlertDialog.Builder(getActivity(), Theme_AppCompat_Light_Dialog);
        builder.setTitle(petName);
        // Set up the input
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input, null);
        final EditText input = (EditText) view.findViewById(R.id.dialog_input);
        // 获取编辑框焦点
        input.setFocusable(true);
        builder.setView(view);
        // Set up the buttons
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newText;
                newText = input.getText().toString();
                if (!newText.equals("")) {
                    updateFoundationInfo(petName, newText);
                    foundation_content.set(position, newText);
                    mFoundationAdapter.notifyItemChanged(position);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //设置可获得焦点
                input.setFocusable(true);
                input.setFocusableInTouchMode(true);
                //请求获得焦点
                input.requestFocus();
                //调用系统输入法
                InputMethodManager inputManager = (InputMethodManager) input
                        .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(input, 0);
            }
        }, 100);
    }

    private int sexSelectDialog(final int position, final String petName) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
//                Theme_DeviceDefault_Light_Dialog_Alert);
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app
                .AlertDialog.Builder(getActivity(), Theme_AppCompat_Light_Dialog);
        builder.setTitle(petName);
        // Set up the input
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_select, null);
        final RadioButton rb_male = (RadioButton) view.findViewById(R.id.dialog_male);
        RadioButton rb_female = (RadioButton) view.findViewById(R.id.dialog_female);
        if (!sIndividualInfo.getSex().equals("男")) {
            rb_male.setChecked(true);
        } else {
            rb_female.setChecked(true);
        }
        builder.setView(view);
        // Set up the buttons
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                foundation_content.set(position, rb_male.isChecked() ? "男" : "女");
                mFoundationAdapter.notifyItemChanged(position);
                sIndividualInfo.setSex(rb_male.isChecked() ? "男" : "女");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        return 0;
    }

    private int wheelViewDialog(final int position, final String petName, final List<String> years,
                                int initPosition) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
//                Theme_DeviceDefault_Light_Dialog_Alert);
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app
                .AlertDialog.Builder(getActivity(), Theme_AppCompat_Light_Dialog);
        builder.setTitle(petName);
        // Set up the input
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wheel, null);
        final WheelView wheelView = (WheelView) view.findViewById(R.id.dialog_wheel_view);
        wheelView.setWheelAdapter(new ArrayWheelAdapter(getActivity()));
        wheelView.setWheelSize(3);
        wheelView.setSkin(WheelView.Skin.Holo);
        wheelView.setWheelData(years);
        wheelView.setSelection(initPosition);
        builder.setView(view);
        // Set up the buttons
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int wheelViewCurrentPosition = wheelView.getCurrentPosition();
                updateFoundationInfo(petName, years.get(wheelViewCurrentPosition));
                foundation_content.set(position, years.get(wheelViewCurrentPosition));
                mFoundationAdapter.notifyItemChanged(position);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        return 0;
    }


    private void updateFoundationInfo(String petName, String newText) {
        switch (petName) {
            case "更新昵称":
                sIndividualInfo.setPetName(newText);
                break;
            case "更新出生年份":
                int birthYear = Integer.parseInt(newText);
                sIndividualInfo.setAge(currentYear - birthYear + "");
                break;
            case "更新身高(CM)":
                sIndividualInfo.setHeight(newText);
                updateStepDuration();
                break;
            case "更新体重(KG)":
                sIndividualInfo.setWeight(newText);
                updateHealthMark();
                break;
        }
    }

    private void updateStepDuration() {
        int age = Integer.parseInt(sIndividualInfo.getAge());
        if (age <= 17) {
            sIndividualInfo.setRunDuration(60);
        } else if (age < 65) {
            sIndividualInfo.setRunDuration(200);
        } else {
            sIndividualInfo.setRunDuration(150);
        }
    }

    private void updateHealthMark() {
        // 重新计算腰围的合理范围并打分
        // 适用于身高，体重，腰围的变化

        MyApplication myApplication = MyApplication.getInstance();
        StringBuilder stringBuffer = new StringBuilder();
        int healthMark = 100;
        // 进行体型的建议
        if (!sIndividualInfo.getWaistLine().equals("")) {
            double waist = Integer.parseInt(sIndividualInfo.getWaistLine());
            double weight = Integer.parseInt(sIndividualInfo.getWeight());
            int age = Integer.parseInt(sIndividualInfo.getAge());
            if (sIndividualInfo.getSex().equals("女")) {
                double a = waist * 0.74;
                double b = (weight * 0.082) + 34.89;
                double result = (a - b) / weight;
                if (age > 30) {
                    if (result < 0.17) {
                        // 扣2分 瘦 提示
                        healthMark = healthMark - 15;
                        stringBuffer.append("体型较瘦，建议您调节饮食。");
                    } else if (result < 0.2) {
                        // 扣1分 偏瘦
                        healthMark = healthMark - 5;
                    } else if (result < 0.27) {
                        // 满分
                    } else if (result < 0.3) {
                        // 扣1分 微胖
                        healthMark = healthMark - 5;
                    } else {
                        // 扣2分 胖
                        healthMark = healthMark - 20;
                        sIndividualInfo.setRunDuration(sIndividualInfo.getRunDuration() + 10);
                        stringBuffer.append("体型较胖，建议您多运动。");
                    }
                } else {
                    if (result < 0.14) {
                        // 扣2分 瘦 提示
                        stringBuffer.append("体型较瘦，建议您调节饮食。");
                        healthMark = healthMark - 15;
                    } else if (result < 0.17) {
                        // 扣1分 偏瘦
                        healthMark = healthMark - 5;
                    } else if (result < 0.24) {
                        // 满分
                    } else if (result < 0.3) {
                        // 扣1分 微胖
                        healthMark = healthMark - 5;
                    } else {
                        // 扣2分 胖
                        healthMark = healthMark - 20;
                        sIndividualInfo.setRunDuration(sIndividualInfo.getRunDuration() + 10);
                        stringBuffer.append("体型较胖，建议您多运动。");
                    }
                }
            } else {
                double a = waist * 0.74;
                double b = (weight * 0.082) + 44.74;
                double result = (a - b) / weight;
                if (age > 30) {
                    if (result < 0.15) {
                        // 扣2分 瘦 提示
                        healthMark = healthMark - 15;
                        stringBuffer.append("体型较瘦，建议您调节饮食。");
                    } else if (result < 0.17) {
                        // 扣1分 偏瘦
                        healthMark = healthMark - 5;
                    } else if (result < 0.23) {
                        // 满分
                    } else if (result < 0.25) {
                        // 扣1分 微胖
                        healthMark = healthMark - 5;
                    } else {
                        // 扣2分 胖
                        healthMark = healthMark - 20;
                        sIndividualInfo.setRunDuration(sIndividualInfo.getRunDuration() + 10);
                        stringBuffer.append("体型较胖，建议您多运动。");
                    }
                } else {
                    if (result < 0.12) {
                        // 扣2分 瘦 提示
                        healthMark = healthMark - 15;
                        stringBuffer.append("体型较瘦，建议您调节饮食。");
                    } else if (result < 0.14) {
                        // 扣1分 偏瘦
                        healthMark = healthMark - 5;
                    } else if (result < 0.20) {
                        // 满分
                    } else if (result < 0.25) {
                        // 扣1分 微胖
                        healthMark = healthMark - 5;
                    } else {
                        // 扣2分 胖
                        healthMark = healthMark - 20;
                        sIndividualInfo.setRunDuration(sIndividualInfo.getRunDuration() + 10);
                        stringBuffer.append("体型较胖，建议您多运动。");
                    }
                }
            }
        }
        //

        SharedPreferences.Editor editor = myApplication.sp.edit();
        editor.putString("tips", stringBuffer.toString());
        editor.apply();
        sIndividualInfo.setHealthMark(healthMark);
    }


    @Override
    public void onStop() {
        mIndInfoLab.updateInfo(sIndividualInfo);
        super.onStop();
    }

    private void enterDialogSupportV7(final int position) {
        android.support.v7.app.AlertDialog.Builder builderV7 = new android.support.v7.app
                .AlertDialog.Builder(getActivity(), Theme_AppCompat_Light_Dialog);
        builderV7.setTitle("更新" + detail_names.get(position));
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input, null);
        final EditText enter = (EditText) view.findViewById(R.id.dialog_input);
        enter.setInputType(TYPE_NUMBER_FLAG_DECIMAL);
        enter.setMaxLines(5);
        builderV7.setView(view);
        builderV7.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newEnter = enter.getText().toString();
                if (!newEnter.equals("")) {
                    detail_content.set(position, newEnter);
                    mDetailAdapter.notifyItemChanged(position);
                    updateDetailInfo(position, newEnter);
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

    private void updateDetailInfo(int position, String newEnter) {
        switch (position) {
            case 0:
                sIndividualInfo.setWaistLine(newEnter);
                break;
            case 1:
                sIndividualInfo.setPulse(newEnter);
                if (Const.stringToInteger(newEnter) > 100 || Const.stringToInteger(newEnter) < 60) {
                    Const.showToast(getActivity(),"建议在平静的情况下测量。");
                }
                break;
            case 2:
                sIndividualInfo.setSystolicPressure(newEnter);
                break;
            case 3:
                sIndividualInfo.setDiastolicPressure(newEnter);
                break;
            case 4:
                sIndividualInfo.setBloodSugar(newEnter);
                break;
            case 5:
                sIndividualInfo.setTC(newEnter);
                break;
        }
        updateHealthMark();
    }
}
