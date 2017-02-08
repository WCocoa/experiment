package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.model.DowOrSerChildObj;

import java.util.ArrayList;

/**
 * ClassName:选择服务内容每块服务内容适配器
 * author: Cocoa
 * date: 2016/9/22.
 */
public class ChoiceServiceCounsellingTimeAdapter extends BaseAdapter {
    private ArrayList<DowOrSerChildObj> list = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private Activity context;
    private Handler handler;

    public ChoiceServiceCounsellingTimeAdapter(Activity context, ArrayList<DowOrSerChildObj> list, Handler handler) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
        this.list = list;
        this.handler = handler;
    }

    public ArrayList<DowOrSerChildObj> getDataList() {
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
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_choice_service_subdesc, null);

            holder.rb_check = (RadioButton) convertView.findViewById(R.id.rb_check);
            holder.money = (TextView) convertView.findViewById(R.id.money);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DowOrSerChildObj cell = list.get(position);

//        holder.rb_check.setText(cell.getDesc());
//        holder.money.setText("￥" + cell.getPrice() + "");


//        if (!isChanged) {
//            holder.rb_check.setTextColor(context.getResources().getColor(R.color.app_item_title));
//            holder.money.setTextColor(context.getResources().getColor(R.color.money_color));
//        } else {
//            holder.rb_check.setTextColor(context.getResources().getColor(R.color.app_item_content));
//            holder.money.setTextColor(context.getResources().getColor(R.color.app_item_content));
//        }
//
//        if (this.position == position) {
//            holder.rb_check.setChecked(!holder.rb_check.isChecked());
//            setIndex(this.position);
//        } else {
//            holder.rb_check.setChecked(false);
//            setIndex(this.position);
//        }

        return convertView;
    }

    class ViewHolder {
        RadioButton rb_check;
        TextView money;
    }

    private boolean isChanged = false;
    private int index = -1;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    private int position = -1;

    public void selectPosition(int position, boolean isChanged) {
        this.position = position;
        this.isChanged = isChanged;
    }

}
