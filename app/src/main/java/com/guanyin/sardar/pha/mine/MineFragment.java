package com.guanyin.sardar.pha.mine;

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
import com.guanyin.sardar.pha.utils.Const;


public class MineFragment extends Fragment {

    private static IndividualInfo sIndividualInfo;
    private IndInfoLab mIndInfoLab;

    private TextView tv_pet_name;
    private TextView tv_sex;
    private TextView tv_birth;
    private TextView tv_weight;
    private TextView tv_height;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initData();
        initView(view);

        tv_pet_name.setText(sIndividualInfo.getPetName());
        tv_sex.setText(sIndividualInfo.getSex());
        tv_birth.setText(sIndividualInfo.getAge());
        tv_height.setText(sIndividualInfo.getHeight());
        tv_weight.setText(sIndividualInfo.getWeight());

        return view;
    }

    private void initData() {
        mIndInfoLab = IndInfoLab.get(getActivity());
        sIndividualInfo = mIndInfoLab.getIndividualInfo();
    }

    private void initView(View view) {
        tv_pet_name = (TextView) view.findViewById(R.id.tv_pet_name);
        tv_sex = (TextView) view.findViewById(R.id.tv_sex);
        tv_birth = (TextView) view.findViewById(R.id.tv_birth);
        tv_height = (TextView) view.findViewById(R.id.tv_height);
        tv_weight = (TextView) view.findViewById(R.id.tv_weight);
    }


}
