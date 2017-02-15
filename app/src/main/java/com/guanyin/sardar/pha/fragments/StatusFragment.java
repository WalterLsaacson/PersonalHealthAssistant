package com.guanyin.sardar.pha.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guanyin.sardar.pha.R;


public class StatusFragment extends Fragment {
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
        return view;
    }
}
