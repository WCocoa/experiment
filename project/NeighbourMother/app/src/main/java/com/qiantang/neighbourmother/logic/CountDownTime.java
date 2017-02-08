package com.qiantang.neighbourmother.logic;

import android.os.Handler;
import android.os.Message;

import com.qiantang.neighbourmother.ui.home.DownOrderActivity;
import com.qiantang.neighbourmother.util.AppLog;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by quliang on 16-10-24.
 */

public class CountDownTime {
    private Handler handler;
    private Timer timer;
private int totalT;
    private boolean isUpUi;

    public CountDownTime(Handler handler) {
        this.handler = handler;
    }


    public void start(int totalT) {
        AppLog.D("totalT:"+totalT);
        this.totalT=totalT;
        isUpUi=true;

        if (timer!=null){
            timer.cancel();
        }

        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sendHandler();
            }
        }, 0, 1000);
    }

    private void sendHandler(){
        if(isUpUi){
            String sTime=null;
            int z=0;
            int y=0;
            z=totalT/60;
            y=totalT%60;

            AppLog.D("z:"+z);
            AppLog.D("y:"+y);

            sTime=getW(z)+":"+getW(y);
            AppLog.D("sTime:"+sTime);
            Message msg=handler.obtainMessage();
        msg.obj=totalT<0?null:sTime;
        msg.what= DownOrderActivity.WHAT_TIME;
            handler.sendMessage(msg);
        }
        totalT--;
    }

    private String getW(int y){
        return  y<10?"0"+y:String.valueOf(y);
    }

    public void onPause() {
        isUpUi=false;
    }
    public void onReStart() {
        isUpUi=true;
    }
    public void onDestory() {
if(timer!=null){
    timer.cancel();
    timer.purge();
    timer=null;
}
    }
}
