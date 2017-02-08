package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.model.CommObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.QLTimeUtil;
import com.qiantang.neighbourmother.widget.CircleImageView;

import java.util.ArrayList;

/**
 * ClassName:订单列表适配器
 * author: Cocoa
 * date: 2016/9/21.
 */
public class CommAdapter extends BaseAdapter {
    private ArrayList<CommObj> list = new ArrayList<CommObj>();
    private LayoutInflater mLayoutInflater;
    private Activity       context;


    public CommAdapter(Activity context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public ArrayList<CommObj> getDataList() {
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
            convertView = mLayoutInflater.inflate(R.layout.item_comm, null);
            holder.floor = (TextView) convertView.findViewById(R.id.floor);
            holder.headImg = (CircleImageView) convertView.findViewById(R.id.headImg);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.com_tag = (TextView) convertView.findViewById(R.id.com_tag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final CommObj item = list.get(position);
        if (position == 0) {
            holder.com_tag.setVisibility(View.VISIBLE);
        } else {
            holder.com_tag.setVisibility(View.GONE);
        }
        ((BaseActivity) context).display(holder.headImg, APIConfig.BASE_IMG_URL + item.getUser_avatar(), R.mipmap.default_img);
        holder.floor.setText(item.getFloor() + "楼");
        holder.name.setText(item.getUser_name());
        holder.content.setText(item.getComment_content());
//        holder.time.setText(QLTimeUtil.getStringTime(item.getCtime()*1000,QLTimeUtil.TIME_MODEL));

        holder.time.setText(QLTimeUtil.getQuLiangTime(item.getCtime() * 1000));
        return convertView;
    }

    class ViewHolder {
        TextView        floor;
        CircleImageView headImg;
        TextView        name;
        TextView        time;
        TextView        content;
        TextView        com_tag;
    }

}