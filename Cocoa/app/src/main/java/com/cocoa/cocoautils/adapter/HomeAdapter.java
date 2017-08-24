package com.cocoa.cocoautils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cocoa.cocoautils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassNmae:
 * Author Cocoa
 * Date 2017/8/17 0017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
    private List<String> list = new ArrayList<>();
    private Context context;

    public HomeAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View       view       = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false);
        HomeHolder homeHolder = new HomeHolder(view);
        return homeHolder;
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {
        holder.mTextView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public HomeHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }

    }
}
