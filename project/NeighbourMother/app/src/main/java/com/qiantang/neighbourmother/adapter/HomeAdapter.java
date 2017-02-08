package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.model.HomeItemChildObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.util.QLScreenUtil;
import com.qiantang.neighbourmother.widget.QRoundAngleImageView;
import com.qiantang.neighbourmother.widget.RatingBar;

import java.util.ArrayList;

/**
 * Created by quliang on 16-9-18.
 */
public class HomeAdapter extends BaseAdapter {
    private ArrayList<HomeItemChildObj> arrayList = new ArrayList<HomeItemChildObj>();
    private LayoutInflater mLayoutInflater;
    private Activity       context;
    private int            dataSize;
    private  DisplayMetrics displayMetrics = new DisplayMetrics();
    public HomeAdapter(Activity context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
                context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    @Override
    public void notifyDataSetChanged() {
        dataSize = arrayList.size();
        super.notifyDataSetChanged();

    }

    public ArrayList<HomeItemChildObj> getDataList() {
        return arrayList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        if (getCount() <= 0 || position >= getCount())
            return null;
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder mHolder = new Holder();
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_home_list, null);
            mHolder.attache_photo = (QRoundAngleImageView) convertView.findViewById(R.id.attache_photo);
            ((BaseActivity) context).calcuAdersWidth(mHolder.attache_photo,0.46f,displayMetrics.widthPixels-(int)QLScreenUtil.dpToPxInt(context, 30));
            mHolder.attache_name = (TextView) convertView.findViewById(R.id.attache_name);
            mHolder.ratingBar1 = (RatingBar) convertView.findViewById(R.id.ratingBar1);
            mHolder.attache_type = (TextView) convertView.findViewById(R.id.attache_type);
            mHolder.details_address = (TextView) convertView.findViewById(R.id.details_address);
            mHolder.attache_sex = (ImageView) convertView.findViewById(R.id.attache_sex);
            mHolder.comeorder = (TextView) convertView.findViewById(R.id.comeorder);
            mHolder.shop_address = (TextView) convertView.findViewById(R.id.shop_address);

            convertView.setTag(mHolder);
        } else {
            mHolder = (Holder) convertView.getTag();
        }
        HomeItemChildObj item = arrayList.get(position);
        ((BaseActivity) context).display(mHolder.attache_photo, APIConfig.BASE_IMG_URL + item.getServant_pic(), R.mipmap.default_img);

        if (item.getUser_name() != null && item.getUser_name().length() > 1) {
            mHolder.attache_name.setText(item.getUser_name().substring(0, 1) + "老师");
        } else {
            mHolder.attache_name.setText(item.getUser_name());
        }
        mHolder.ratingBar1.setStar(item.getUser_star());
        StringBuilder builder = new StringBuilder();
        if (item.getServant_type_string() != null && item.getServant_type_string().length() > 1) {
            String[] arr = item.getServant_type_string().split(" ");
            for (int i = 0; i < arr.length; i++) {
                builder.append(arr[i]);
                if (i == arr.length - 1) {
                    builder.append("");
                } else {
                    builder.append(",");
                }
            }
        }

        mHolder.shop_address.setVisibility(TextUtils.isEmpty(item.getShop_name()) ? View.GONE : View.VISIBLE);
        mHolder.attache_type.setText(builder.toString());
        mHolder.details_address.setText(item.getUser_address());
        mHolder.comeorder.setText(context.getResources().getString(R.string.homefrag_item_come_order_number) + item.getOrder_number());
        if (!TextUtils.isEmpty(item.getShop_name())) {
            mHolder.shop_address.setVisibility(View.VISIBLE);
            mHolder.shop_address.setText(item.getShop_name());
        } else {
            mHolder.shop_address.setVisibility(View.GONE);
        }
        mHolder.attache_sex.setSelected(item.getUser_gender() == 2);

        return convertView;
    }

    private class Holder {
        QRoundAngleImageView attache_photo;//头像
        TextView             attache_name;//姓名
        RatingBar            ratingBar1;//评分
        TextView             attache_type;//服务类型
        TextView             details_address;//详细地址
        TextView             comeorder;//接单量
        ImageView            attache_sex;//性别
        TextView             shop_address;//店名
    }

}