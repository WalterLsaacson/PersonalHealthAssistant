package com.guanyin.sardar.personalhealthassistant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanyin.sardar.personalhealthassistant.R;
import com.guanyin.sardar.personalhealthassistant.model.Clock;
import com.guanyin.sardar.personalhealthassistant.model.ClockLab;

import java.util.List;


public class AlertFragment extends Fragment {

    RecyclerView mRecyclerView;
    int[] images;
    ClockAdapter mClockAdapter;


    public static AlertFragment newInstance() {

        Bundle args = new Bundle();

        AlertFragment fragment = new AlertFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.clock_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        updateUI();
        return view;
    }

    private void updateUI() {

        images = new int[]{R.drawable.bloodpressure, R.drawable.sleep, R.drawable.sport,
                R.drawable.medicine, R.drawable.longtimeseat};
        ClockLab mClockLab = ClockLab.get(getActivity());
        List<Clock> clocks = mClockLab.getClocks();

        if (mClockAdapter == null) {
            mClockAdapter = new ClockAdapter(clocks);
            mRecyclerView.setAdapter(mClockAdapter);
        } else {
            mRecyclerView.setAdapter(mClockAdapter);
        }
    }

    // viewHolder
    private class ClockHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mIcon;
        TextView mTitle;
        TextView mOpened;
        TextView mTime;

        public ClockHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.brief_icon);
            mTitle = (TextView) itemView.findViewById(R.id.brief_title);
            mOpened = (TextView) itemView.findViewById(R.id.brief_opened);
            mTime = (TextView) itemView.findViewById(R.id.brief_time);
            itemView.setOnClickListener(this);
        }

        public void bindClock(com.guanyin.sardar.personalhealthassistant.model.Clock mClock, int
                position) {
            mIcon.setBackgroundResource(images[position]);
            mTitle.setText(mClock.getTitle());
            mOpened.setText(mClock.isOpen() ? "开启" : "未开启");
            mTime.setText(mClock.getTitle());
        }

        @Override
        public void onClick(View v) {

        }
    }

    //RecycleView Adapter
    private class ClockAdapter extends RecyclerView.Adapter<ClockHolder> {

        List<Clock> mClocks;

        public ClockAdapter(List<Clock> clocks) {
            mClocks = clocks;
        }

        @Override
        public ClockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.clock_item_brief, parent, false);
            return new ClockHolder(view);
        }

        @Override
        public void onBindViewHolder(ClockHolder holder, int position) {
            holder.bindClock(mClocks.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mClocks.size();
        }
    }
}
