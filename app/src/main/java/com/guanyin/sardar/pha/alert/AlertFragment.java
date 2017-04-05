package com.guanyin.sardar.pha.alert;

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

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.alert.model.Clock;
import com.guanyin.sardar.pha.alert.model.ClockLab;

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
        images = new int[]{R.drawable.tooth, R.drawable.longtimeseat, R.drawable.bloodpressure,
                R.drawable.sleep, R.drawable.weight, R.drawable.medicine,};
        View view = inflater.inflate(R.layout.fragment_alert, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.clock_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ClockLab mClockLab = ClockLab.get(getActivity());
        List<Clock> clocks = mClockLab.getClocks();

        mClockAdapter = new ClockAdapter(clocks);
        mRecyclerView.setAdapter(mClockAdapter);
    }

    // viewHolder
    private class ClockHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mIcon;
        TextView mTitle;
        TextView mOpened;
        TextView mTime;

        Clock mClock;

        ClockHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.brief_icon);
            mTitle = (TextView) itemView.findViewById(R.id.brief_title);
            mOpened = (TextView) itemView.findViewById(R.id.brief_opened);
            mTime = (TextView) itemView.findViewById(R.id.brief_time);
            itemView.setOnClickListener(this);
        }

        void bindClock(Clock clock) {
            mClock = clock;
            mIcon.setBackgroundResource(images[Integer.parseInt(clock.getId())]);
            mTitle.setText(mClock.getTitle());
            mOpened.setText(mClock.isOpen() ? "开启" : "未开启");
            mTime.setText(mClock.getDate());
        }

        @Override
        public void onClick(View v) {
            startActivity(ClockEditActivity.newIntent(getActivity(), mClock.getId()));
        }
    }

    //RecycleView Adapter
    private class ClockAdapter extends RecyclerView.Adapter<ClockHolder> {

        List<Clock> mClocks;

        ClockAdapter(List<Clock> clocks) {
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
            holder.bindClock(mClocks.get(position));
        }

        @Override
        public int getItemCount() {
            return mClocks.size();
        }
    }
}
