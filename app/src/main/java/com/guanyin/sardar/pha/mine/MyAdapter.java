package com.guanyin.sardar.pha.mine;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;

import java.util.ArrayList;


class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private ArrayList<String> names;
    private ArrayList<String> content;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public MyAdapter(ArrayList<String> foundation_names, ArrayList<String> foundation_content) {
        names = foundation_names;
        content = foundation_content;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info,
                parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.item_name.setText(names.get(position));
        viewHolder.item_content.setText(content.get(position));
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_name;
        TextView item_content;

        ViewHolder(View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_content = (TextView) itemView.findViewById(R.id.item_content);
        }
    }

}
