package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.business.response.DiscussionGroupResp;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.widget.RoundAngleImageView;

import java.util.ArrayList;

/**
 * ClassName:订单列表适配器
 * author: Cocoa
 * date: 2016/9/21.
 */
public class DiscussionGroupAdapter extends BaseAdapter {
    private ArrayList<DiscussionGroupResp> list = new ArrayList<DiscussionGroupResp>();
    private LayoutInflater mLayoutInflater;
    private Activity       context;


    public DiscussionGroupAdapter(Activity context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public ArrayList<DiscussionGroupResp> getDataList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (getCount() <= 0 || position >= getCount())
            return null;
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_discussiongroup, null);
            holder.image = (RoundAngleImageView) convertView.findViewById(R.id.image);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.summary = (TextView) convertView.findViewById(R.id.summary);
            holder.intogroup = (TextView) convertView.findViewById(R.id.intogroup);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final DiscussionGroupResp item = list.get(position);

        ((BaseActivity) context).display(holder.image, APIConfig.BASE_IMG_URL + item.getGroup_pic(), R.mipmap.default_img);
        holder.title.setText(item.getGroup_name());
        holder.summary.setText(item.getGroup_introduce());
        if (item.getJoin() == 1) {
            holder.intogroup.setVisibility(View.VISIBLE);
        } else {
            holder.intogroup.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder {
        RoundAngleImageView image;
        TextView  title;
        TextView  summary;
        TextView  intogroup;
    }

}
