package com.qiantang.neighbourmother.ui.activity;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.home.DownOrderActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;
import com.qiantang.neighbourmother.util.QLTimeUtil;
import com.qiantang.neighbourmother.util.ToastUtils;

import java.util.Calendar;

/**
 * Created by quliang on 16-8-16.
 */
public class TimePickerActivity extends BaseActivity implements View.OnClickListener{
    private ImageView back;
    private TextView datap_sure;
    private TimePicker timePicker;

    private String stime;
    @Override
    public int getContentView() {
        return R.layout.activity_timepicker;
    }

    @Override
    public void initView() {
        back=(ImageView)findViewById(R.id.back);
        datap_sure=(TextView)findViewById(R.id.datap_sure);
        timePicker=(TimePicker)findViewById(R.id.datePicker);
    }

    @Override
    public void initData() {
//stime=Calendar.getInstance().get(Calendar.YEAR)+"-"+getTime(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+getTime(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        stime= QLTimeUtil.getStringTime(System.currentTimeMillis(),"HH:mm");
        AppLog.D("stime:"+stime);
    }

    @Override
    public void initEvent() {
        datap_sure.setOnClickListener(this);
        back.setOnClickListener(this);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                AppLog.D("hourOfDay:"+hourOfDay);
                AppLog.D("minute:"+minute);

                stime=getTime(hourOfDay)+":"+getTime(minute);
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
                if(TextUtils.isEmpty(stime)){
                    ToastUtils.toastLong(this,R.string.datapicker_submit_desc);
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra(IntentFinal.INTENT_TIME_STRING,stime);
                setResult(DownOrderActivity.RESULT_CHOICE_START_TIME,intent);
                finish();

                break;
        }
    }
}
