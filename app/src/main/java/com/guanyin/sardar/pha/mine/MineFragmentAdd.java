package com.guanyin.sardar.pha.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.R.style.Theme_DeviceDefault_Light_Dialog_Alert;


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
        detail_names.add("腰围");
        detail_content.add(sIndividualInfo.getWaistLine());
        detail_names.add("体温");
        detail_content.add(sIndividualInfo.getTemperature());
        detail_names.add("脉搏");
        detail_content.add(sIndividualInfo.getPulse());
        detail_names.add("血压");
        detail_content.add(sIndividualInfo.getBloodPressure());
        detail_names.add("血糖");
        detail_content.add(sIndividualInfo.getBloodSugar());
        detail_names.add("血脂");
        detail_content.add(sIndividualInfo.getBloodFat());
    }

    private void initView(View view) {
        RecyclerView foundation_recyclerView = (RecyclerView) view.findViewById(R.id
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
        detail_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        detail_recyclerView.setHasFixedSize(true);
        mDetailAdapter = new MyAdapter(detail_names, detail_content);
        detail_recyclerView.setAdapter(mDetailAdapter);
        mDetailAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Const.log("DDDDDDDitemclick", position + "");
            }
        });
    }

    private int inputDialog(final int position, final String petName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                Theme_DeviceDefault_Light_Dialog_Alert);
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
        return 0;
    }

    private int sexSelectDialog(final int position, final String petName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                Theme_DeviceDefault_Light_Dialog_Alert);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                Theme_DeviceDefault_Light_Dialog_Alert);
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

}
