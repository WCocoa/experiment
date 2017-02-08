package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.model.DiscussionUserObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:专员列表适配器
 * author: Cocoa
 * date: 2016/9/21.
 */
public class DiscGroupImgHeadAdapter extends RecyclerView.Adapter<DiscGroupImgHeadAdapter.ViewHolder> {
    private List<DiscussionUserObj> list = new ArrayList<DiscussionUserObj>();
    private LayoutInflater mLayoutInflater;
    private Activity context;

    public DiscGroupImgHeadAdapter(Activity context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public List<DiscussionUserObj> getDataList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_discussiongroup_headimg, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((BaseActivity)context).display(holder.imgh, APIConfig.BASE_IMG_URL+getDataList().get(position).getUser_avatar(),R.mipmap.default_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgh;

        public ViewHolder(View itemView) {
            super(itemView);
            imgh = (CircleImageView) itemView.findViewById(R.id.imgh);
        }
    }
}
