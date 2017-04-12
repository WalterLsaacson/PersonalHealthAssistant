package com.guanyin.sardar.pha.mine;

import android.content.Context;
import android.content.DialogInterface;
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
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.support.v7.appcompat.R.style.Theme_AppCompat_Light_Dialog;
import static android.text.InputType.TYPE_CLASS_NUMBER;


public class MineFragmentAdd extends Fragment {

    private static IndividualInfo sIndividualInfo;
    private IndInfoLab mIndInfoLab;

    MyAdapter mDetailAdapter;
    MyAdapter mFoundationAdapter;

    ArrayList<String> foundation_names;
    ArrayList<String> foundation_content;

    ArrayList<String> detail_names;
    ArrayList<String> detail_content;

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
        foundation_names.add("年龄");
        foundation_content.add(sIndividualInfo.getAge());
        foundation_names.add("身高(CM)");
        foundation_content.add(sIndividualInfo.getHeight());
        foundation_names.add("体重(KG)");
        foundation_content.add(sIndividualInfo.getWeight());
        // 详细信息
        detail_names = new ArrayList<>();
        detail_content = new ArrayList<>();
        detail_names.add("腰围(CM)");
        detail_content.add(sIndividualInfo.getWaistLine());
        detail_names.add("体温(℃)");
        detail_content.add(sIndividualInfo.getTemperature());
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
        detail_names.add("血脂(TG mmol/L)");
        detail_content.add(sIndividualInfo.getTG());
        detail_names.add("血脂(LDL_C mmol/L)");
        detail_content.add(sIndividualInfo.getLDL_L());
        detail_names.add("血脂(HDL_C mmol/L)");
        detail_content.add(sIndividualInfo.getHDL_C());
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
                        inputDialog(position, "修改昵称");
                        break;
                    case 1:
                        sexSelectDialog(position, "更新性别");
                        break;
                    case 2:
                        int initYear = Integer.parseInt(sIndividualInfo.getAge());
                        int initYPosition = 0;
                        List<String> years = new ArrayList<>();
                        Calendar calendar = Calendar.getInstance();
                        final int currentYear = calendar.get(Calendar.YEAR) - 3;
                        for (int i = 1980; i <= currentYear; i++) {
                            years.add(i + "");
                            if (i == initYear) {
                                initYPosition = years.size() - 1;
                            }
                        }
                        wheelViewDialog(position, "生日修改", years, initYPosition);
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
                        wheelViewDialog(position, "身高修改", height, initHPosition);
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
                        wheelViewDialog(position, "体重修改", weight, initWPosition);
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
//                    updateEnterInfo(position, newEnter);
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

    private void updateEnterInfo(int position, String newEnter) {
        switch (position) {
            case 0:
                sIndividualInfo.setWaistLine(newEnter);
                break;
            case 1:
                sIndividualInfo.setTemperature(newEnter);
                break;
            case 2:
                sIndividualInfo.setPulse(newEnter);
                break;
            case 3:
                sIndividualInfo.setSystolicPressure(newEnter);
                break;
            case 4:
                sIndividualInfo.setDiastolicPressure(newEnter);
                break;
            case 5:
                sIndividualInfo.setBloodSugar(newEnter);
                break;
            case 6:
                sIndividualInfo.setTC(newEnter);
                break;
            case 7:
                sIndividualInfo.setTG(newEnter);
                break;
            case 8:
                sIndividualInfo.setLDL_L(newEnter);
                break;
            case 9:
                sIndividualInfo.setHDL_C(newEnter);
                break;
        }
    }

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
                    updateIndividualInfo(petName, newText);
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
                updateIndividualInfo(petName, years.get(wheelViewCurrentPosition));
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

    private void updateIndividualInfo(String petName, String newText) {
        switch (petName) {
            case "修改昵称":
                sIndividualInfo.setPetName(newText);
                break;
            case "生日修改":
                sIndividualInfo.setAge(newText);
                break;
            case "身高修改":
                sIndividualInfo.setHeight(newText);
                break;
            case "体重修改":
                sIndividualInfo.setWeight(newText);
                break;
        }
    }

    @Override
    public void onStop() {
        mIndInfoLab.updateInfo(sIndividualInfo);
        super.onStop();
    }

    // 内部类用于实现禁止recycleView滑动
//    private class CustomLayoutManager extends LinearLayoutManager {
//
//        private boolean isEnableScrolled;
//
//        CustomLayoutManager(Context context) {
//            super(context);
//        }
//
//        void setEnableScrolled(boolean isEnableScrolled) {
//            this.isEnableScrolled = isEnableScrolled;
//        }
//
//        @Override
//        public boolean canScrollVertically() {
//            return isEnableScrolled && super.canScrollVertically();
//        }
//    }
    private void enterDialogSupportV7(final int position) {
        android.support.v7.app.AlertDialog.Builder builderV7 = new android.support.v7.app
                .AlertDialog.Builder(getActivity(), Theme_AppCompat_Light_Dialog);
        builderV7.setTitle("更新" + detail_names.get(position));
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input, null);
        final EditText enter = (EditText) view.findViewById(R.id.dialog_input);
        enter.setInputType(TYPE_CLASS_NUMBER);
        builderV7.setView(view);
        builderV7.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newEnter = enter.getText().toString();
                if (!newEnter.equals("")) {
                    updateEnterInfo(position, newEnter);
                    detail_content.set(position, newEnter);
                    mDetailAdapter.notifyItemChanged(position);
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

}
