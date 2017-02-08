package com.qiantang.neighbourmother.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by Administrator on 2015/12/16.
 */
public class MyBroadCast {
    private MyBroadCastListener myBroadCastListener;
    private AppBroadCast appBroadCast;
   private String[] actions;
   private Context context;

    public MyBroadCast(Context context,String[] actions) {
this.actions=actions;
        this.context=context;
        init();
    }

    private void init(){
        appBroadCast=new AppBroadCast();
        IntentFilter mIntentFilter=new IntentFilter();

        for (String action:actions){
            mIntentFilter.addAction(action);
        }
        context.registerReceiver(appBroadCast, mIntentFilter);
    }

    private class AppBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(myBroadCastListener!=null)
            myBroadCastListener.onReceive(context,intent);
        }
    }

    public void setOnMyBroadCastListener(MyBroadCastListener myBroadCastListener){
        this.myBroadCastListener=myBroadCastListener;
    }

    public interface MyBroadCastListener{
       void onReceive(Context context, Intent intent);
    }

    public void onDestroy(){
        if(appBroadCast!=null)
            context.unregisterReceiver(appBroadCast);
    }
}
