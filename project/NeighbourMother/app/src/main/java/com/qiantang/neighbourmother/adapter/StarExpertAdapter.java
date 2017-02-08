package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.response.StarListResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.community.ConmmunityPersonalActivity;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:明星达人适配器
 * author: Cocoa
 * date: 2016/9/21.
 */
public class StarExpertAdapter extends RecyclerView.Adapter<StarExpertAdapter.ViewHolder> {
    public List<StarListResp> getList() {
        return list;
    }

    public void setList(List<StarListResp> list) {
        this.list = list;
    }

    private List<StarListResp> list = new ArrayList<StarListResp>();
    private Activity context;

    public StarExpertAdapter(Activity context) {
        this.context = context;
    }


    @Override
    public StarExpertAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_star_expert_list, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StarExpertAdapter.ViewHolder holder, final int position) {
        holder.star_name.setText(list.get(position).getUser_name());
        ((BaseActivity) context).display(holder.star_image1, APIConfig.BASE_IMG_URL + list.get(position).getUser_avatar(), R.mipmap.default_img);
        holder.focus_num.setText(list.get(position).getFans());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConmmunityPersonalActivity.class);
                intent.putExtra(IntentFinal.INTENT_USER_ID_TO_USER_DETAILS, list.get(position).getUser_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView star_image1;//图像
        public TextView        star_name;//名称
        public TextView        focus_num;//关注数

        public ViewHolder(View itemView) {
            super(itemView);
            star_image1 = (CircleImageView) itemView.findViewById(R.id.star_image1);
            star_name = (TextView) itemView.findViewById(R.id.star_name);
            focus_num = (TextView) itemView.findViewById(R.id.focus_num);
        }
    }
}