package com.qiantang.neighbourmother.ui.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.home.DownOrderActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.QLTimeUtil;

import java.util.Calendar;

/**
 * Created by quliang on 16-8-16.
 */
public class DataPickerActivity extends BaseActivity implements View.OnClickListener{
    private ImageView back;
    private TextView datap_sure;
    private DatePicker datePicker;

    private String stime;
    @Override
    public int getContentView() {
        return R.layout.activity_datapicker;
    }

    @Override
    public void initView() {
        back=(ImageView)findViewById(R.id.back);
        datap_sure=(TextView)findViewById(R.id.datap_sure);
        datePicker=(DatePicker)findViewById(R.id.datePicker);
    }

    @Override
    public void initData() {
stime= QLTimeUtil.getStringTime(System.currentTimeMillis(),"yyyy年MM月dd日");
        AppLog.D("stime:"+stime);
    }

    @Override
    public void initEvent() {
        datap_sure.setOnClickListener(this);
        back.setOnClickListener(this);
        datePicker.init(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                AppLog.D("year:"+year);
                AppLog.D("monthOfYear:"+monthOfYear);
                AppLog.D("dayOfMonth:"+dayOfMonth);
                 stime=year+"年"+getTime(monthOfYear+1)+"月"+getTime(dayOfMonth)+"日";
                AppLog.D("stime:"+stime);
            }
        });
    }

    @Override
    protected void updateUI(Message msg) {

    }

    private String getTime(int time){
        String stime=null;
        if(time<10){
            stime="0"+time;
    }else{
        stime=String.valueOf(time);
    }
return stime;
}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.datap_sure:

                long time= QLTimeUtil.getLongTime(stime,QLTimeUtil.TIME_MODEL_CHINA);
                Intent intent=new Intent();
                intent.putExtra(IntentFinal.INTENT_DATA_STRING,time);
setResult(DownOrderActivity.RESULT_CHOICE_DATA,intent);
                    finish();

                break;
        }
    }

}
