package com.guanyin.sardar.pha.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.mine.model.IndividualInfo;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;


public class BodyInfoFragment extends Fragment {

    WheelView heightWheelView;
    ArrayList heightList;
    TextView tv_height;
    WheelView weightWheelView;
    ArrayList weightList;
    TextView tv_weight;

    IndividualInfo mIndividualInfo;

    public static final String EXTRA_BODY_FRAGMENT = "individual";

    public static BodyInfoFragment newInstance(IndividualInfo individualInfo) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_BODY_FRAGMENT, individualInfo);
        BodyInfoFragment fragment = new BodyInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_info, container, false);

        // 获取数据包
        mIndividualInfo = (IndividualInfo) getArguments().getSerializable(EXTRA_BODY_FRAGMENT);
        // TODO 寻找更好的组件放弃当前组件，因为有warning
        heightWheelView = (WheelView) view.findViewById(R.id.height_wheel_view);
        heightWheelView.setWheelAdapter(new ArrayWheelAdapter(getActivity()));
        heightWheelView.setWheelSize(3);
        heightWheelView.setSkin(WheelView.Skin.Holo);
        heightList = new ArrayList<>();
        for (int i = 150; i <= 210; i++) {
            heightList.add(i + "");
        }
        heightWheelView.setWheelData(heightList);
        tv_height = (TextView) view.findViewById(R.id.tv_height);
        heightWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                String height = heightList.get(position).toString() + " CM";
                tv_height.setText(height);
                mIndividualInfo.setHeight(heightList.get(position).toString());
            }
        });

        weightWheelView = (WheelView) view.findViewById(R.id.weight_wheel_view);
        weightWheelView.setWheelAdapter(new ArrayWheelAdapter(getActivity()));
        weightWheelView.setWheelSize(3);
        weightWheelView.setSkin(WheelView.Skin.Holo);
        weightList = new ArrayList<>();
        for (int i = 40; i <= 150; i++) {
            weightList.add(i + "");
        }
        weightWheelView.setWheelData(weightList);
        tv_weight = (TextView) view.findViewById(R.id.tv_weight);
        weightWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                String weight = weightList.get(position).toString() + " KG";
                tv_weight.setText(weight);
                mIndividualInfo.setWeight(weightList.get(position).toString());
            }
        });

        setDefault();

        return view;
    }

    private void setDefault() {
        // 男性  170  65   女性 155 45
        if (mIndividualInfo.getSex().equals("男")) {
            heightWheelView.setSelection(20);
            weightWheelView.setSelection(25);
        } else {
            heightWheelView.setSelection(5);
            weightWheelView.setSelection(5);
        }
//        heightWheelView.setSelection();
//        weightWheelView.setSelection()
    }
}
