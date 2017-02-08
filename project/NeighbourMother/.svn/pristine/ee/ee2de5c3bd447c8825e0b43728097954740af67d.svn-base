package com.qiantang.neighbourmother.adapter;

import android.app.Activity;
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
public class ChoiceServiceAdapter extends BaseAdapter {
    private ArrayList<DowOrSerChildObj> list = new ArrayList<DowOrSerChildObj>();
    private LayoutInflater mLayoutInflater;
    private Activity context;

    private boolean enable=true;
    private boolean checked;
    private int checkedIndex=-1;

    /*add extra */
    private boolean isFd;


    public ChoiceServiceAdapter(Activity context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
    }


    public void setFd(boolean fd) {
        isFd = fd;
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

        holder.rb_check.setText(cell.getService_name());

        if (enable) {
            holder.rb_check.setTextColor(context.getResources().getColor(R.color.app_item_title));
            holder.money.setTextColor(context.getResources().getColor(R.color.money_color));
        } else {
            holder.rb_check.setTextColor(context.getResources().getColor(R.color.app_item_content));
            holder.money.setTextColor(context.getResources().getColor(R.color.app_item_content));
        }

        if(isFd){
            holder.money.setText("￥" + new java.text.DecimalFormat("0.00").format((float)cell.getFdMoney()/100));
            if(cell.isChekced()) holder.money.setVisibility(View.VISIBLE);
            else holder.money.setVisibility(View.GONE);
        }else{
            holder.money.setVisibility(View.VISIBLE);
            holder.money.setText("￥" + new java.text.DecimalFormat("0.00").format((float)cell.getService_money()/100));
        }


        holder.rb_check.setChecked(cell.isChekced());

        return convertView;
    }

    class ViewHolder {
        RadioButton rb_check;
        TextView money;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {

        this.enable = enable;
        if(!enable){
      for (DowOrSerChildObj dowOrSerChildObj:list){
          dowOrSerChildObj.setChekced(false);
      }
            checked=false;
            if(checkEnable!=null)
                checkEnable.onCheck(enable);
        }
    }


    private CheckEnable checkEnable;

    public void setCheckEnableListener(CheckEnable checkEnable){
        this.checkEnable=checkEnable;
    }
    public interface CheckEnable{
        void onCheck(boolean enable);
    }

    public int getCheckedIndex() {
        return checkedIndex;
    }

    public void setCheckedIndex(int checkedIndex) {
        this.checkedIndex = checkedIndex;
    }

    //    private boolean isChanged = false;
//    private int index = -1;
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    private int position = -1;
//
//    public void selectPosition(int position, boolean isChanged) {
//        this.position = position;
//        this.isChanged = isChanged;
//    }

}
