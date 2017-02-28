package com.guanyin.sardar.pha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.utils.Const;


public class MineFragment extends Fragment {
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
        view.findViewById(R.id.testAddBadgeItem).setOnClickListener(new View
                .OnClickListener() {


            @Override
            public void onClick(View v) {
                Const.showToast(getActivity(),"test toast");
            }
        });
        return view;
    }
}
