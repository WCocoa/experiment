package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.model.PostObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.community.ConmmunityPersonalActivity;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.QLTimeUtil;
import com.qiantang.neighbourmother.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:社群主页适配器
 * author: Cocoa
 * date: 2016/9/21.
 */
public class ConmmunityAdapter extends BaseAdapter {
    private List<PostObj> list = new ArrayList<PostObj>();
    private LayoutInflater mLayoutInflater;
    private Activity       context;

    public ConmmunityAdapter(Activity context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public List<PostObj> getDataList() {
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
            convertView = mLayoutInflater.inflate(R.layout.item_conmmunity_list, null);
            holder.conmmunity_title = (TextView) convertView.findViewById(R.id.conmmunity_title);
            holder.conmmunity_tag = (TextView) convertView.findViewById(R.id.conmmunity_tag);
            holder.conmmunity_image = (ImageView) convertView.findViewById(R.id.conmmunity_image);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.conmmunity_time = (TextView) convertView.findViewById(R.id.conmmunity_time);
            holder.conmmunity_focus = (TextView) convertView.findViewById(R.id.conmmunity_focus);
            holder.comment_num = (TextView) convertView.findViewById(R.id.comment_num);
            holder.user_image = (CircleImageView) convertView.findViewById(R.id.user_image);
            holder.rl_user_info = (LinearLayout) convertView.findViewById(R.id.rl_user_info);
            holder.conmmunity_praise = (ImageView) convertView.findViewById(R.id.conmmunity_praise);
            calcuAdersWidth(holder.conmmunity_image, 0.5f);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final PostObj item = list.get(position);
        holder.conmmunity_title.setText(item.getPost_title());
        StringBuilder builder = new StringBuilder();
        if (item.getLabel() != null && item.getLabel().length > 0)

            for (int i = 0; i < item.getLabel().length; i++) {
                builder.append(item.getLabel()[i]);
                if (i == item.getLabel().length - 1) {
                    builder.append("");
                } else {
                    builder.append(" , ");
                }
            }
        holder.conmmunity_tag.setText(builder.toString());
        ((BaseActivity) context).display(holder.conmmunity_image, APIConfig.BASE_IMG_URL + item.getPost_pic(), R.mipmap.default_img);
        ((BaseActivity) context).display(holder.user_image, APIConfig.BASE_IMG_URL + item.getUser_avatar(), R.mipmap.default_img);
        holder.name.setText(item.getUser_name());
        holder.conmmunity_time.setText(QLTimeUtil.getQuLiangTime(item.getCtime() * 1000));
        holder.conmmunity_focus.setText(item.getLike());
        holder.comment_num.setText(item.getComment());
        holder.conmmunity_praise.setSelected(item.getLiked() == 1 ? true : false);

        holder.rl_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConmmunityPersonalActivity.class);
                intent.putExtra(IntentFinal.INTENT_USER_ID_TO_USER_DETAILS, item.getUser_id());
                context.startActivityForResult(intent, 3);
            }
        });
        return convertView;
    }

    public void calcuAdersWidth(final View view, final float scale) {

        view.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams   = view.getLayoutParams();
                DisplayMetrics         displayMetrics = new DisplayMetrics();
                context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                layoutParams.height = (int) (displayMetrics.widthPixels * scale);
                view.setLayoutParams(layoutParams);
                view.postInvalidate();
            }
        });

    }

    class ViewHolder {
        TextView        conmmunity_title;//标题
        TextView        conmmunity_tag;//标签
        ImageView       conmmunity_image;//内容图片
        TextView        name;//发帖人姓名
        TextView        conmmunity_time;//发帖时间
        TextView        conmmunity_focus;//关注数
        TextView        comment_num;//评论数
        LinearLayout    rl_user_info;//点击用户信息跳转到用户帖子详情
        CircleImageView user_image;//用户头像
        ImageView       conmmunity_praise;//点赞
    }

}
