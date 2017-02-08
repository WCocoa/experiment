package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.model.RechargeObj;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.ArrayList;

/**
 * ClassName:充值记录适配器
 * author: Cocoa
 * date: 2016/9/21.
 */
public class RechargeAdapter extends BaseAdapter {
    private ArrayList<RechargeObj> list = new ArrayList<RechargeObj>();
    private LayoutInflater mLayoutInflater;
    private Activity       context;

    public RechargeAdapter(Activity context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public ArrayList<RechargeObj> getDataList() {
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
            convertView = mLayoutInflater.inflate(R.layout.item_recharge, null);
            holder.rela_item = (RelativeLayout) convertView.findViewById(R.id.rela_item);
            holder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            holder.tv_give = (TextView) convertView.findViewById(R.id.tv_give);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RechargeObj cell=list.get(position);

        holder.rela_item.setBackgroundResource(cell.isSelected()?R.drawable.recharge_item_bg_selected:R.drawable.recharge_item_bg);
        holder.tv_money.setTextColor(cell.isSelected()?context.getResources().getColor(R.color.recharge_tv_selected):context.getResources().getColor(R.color.recharge_tv_normal));

        holder.tv_money.setText("￥"+cell.getMoney()/100);

        holder.tv_give.setText("送￥"+cell.getGive()/100);
        holder.tv_give.setVisibility(cell.getGive()>0?View.VISIBLE:View.GONE);
        return convertView;
    }


    class ViewHolder {
        RelativeLayout rela_item;
        TextView     tv_money;
        TextView     tv_give;
    }

    public void selected(int index){
        for (RechargeObj rechargeObj:list) {
            rechargeObj.setSelected(false);
        }
        if(index>=0&&index<list.size())
        list.get(index).setSelected(true);
        notifyDataSetChanged();
    }

    public RechargeObj getSelected(){
        RechargeObj rechargeObj1=null;
        for (RechargeObj rechargeObj:list) {
            if(rechargeObj.isSelected()){
                rechargeObj1=rechargeObj;
                break;
            }
        }
        return rechargeObj1;
    }




}
