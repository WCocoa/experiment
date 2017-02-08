package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.response.RechargeMoneyResp;
import com.qiantang.neighbourmother.util.QLTimeUtil;

import java.util.ArrayList;

/**
 * ClassName:充值记录适配器
 * author: Cocoa
 * date: 2016/9/21.
 */
public class PayRecordAdapter extends BaseAdapter {
    private ArrayList<RechargeMoneyResp> list = new ArrayList<RechargeMoneyResp>();
    private LayoutInflater mLayoutInflater;
    private Activity       context;

    public PayRecordAdapter(Activity context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public ArrayList<RechargeMoneyResp> getDataList() {
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

    //    private ArrayList<String> text = new ArrayList<>(Arrays.asList("步行", "看护", "周末看护", "辅导", "专车", "拼车"));
//    private ArrayList<String> text = new ArrayList<>(Arrays.asList("步行", "看护", "周末看护"));

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_pay_record, null);
            holder.pay_year = (TextView) convertView.findViewById(R.id.pay_year);
            holder.pay_month_day = (TextView) convertView.findViewById(R.id.pay_month_day);
            holder.pay_way = (ImageView) convertView.findViewById(R.id.pay_way);
            holder.pay_money = (TextView) convertView.findViewById(R.id.pay_money);
            holder.pay_record_money = (TextView) convertView.findViewById(R.id.pay_record_money);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RechargeMoneyResp item = list.get(position);
        holder.pay_month_day.setText(QLTimeUtil.getStringTime((item.getRecharge_time() * 1000), "MM-dd"));
        holder.pay_year.setText(QLTimeUtil.getStringTime((item.getRecharge_time() * 1000), "yyyy"));
        holder.pay_money.setText("-￥" + String.format("%1.2f", item.getRecharge_money() / 100));
        holder.pay_record_money.setText("+" + String.format("%1.2f", item.getRecharge_money_real() / 100));
//        if (position==5){
//            holder.pay_money.setText("-￥" + String.format("%1.2f", 99999.99));
//            holder.pay_record_money.setText("+" + String.format("%1.2f", 99999.99));
//        }
        switch (item.getPay_platform()) {
            case 1:
                holder.pay_way.setSelected(false);
                break;
            case 2:
                holder.pay_way.setSelected(true);
                break;
            default:
                holder.pay_way.setVisibility(View.INVISIBLE);
                break;
        }

        return convertView;
    }


    class ViewHolder {
        TextView  pay_year;//年
        TextView  pay_month_day;//月、日
        ImageView pay_way;//支付方式显示图片
        TextView  pay_money;//支付金额
        TextView  pay_record_money;//充值金额
    }

}
