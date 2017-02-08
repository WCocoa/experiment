package com.qiantang.neighbourmother.logic;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.QLScreenUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author quliang
 * @version 2015-9-2 上午9:41:39 desc 创建标签文本
 */
abstract class CreateAdPagerBase {
    protected Activity     activity;
    protected LinearLayout ll;
    protected ViewPager    viewPager;
    protected int          currentItem;

    protected Handler handler;
    protected Timer   timer;
    protected int     what;

    protected boolean isAotuPageTurning;

    public CreateAdPagerBase(Activity activity, LinearLayout ll,
                             ViewPager viewPager, Handler handler, int what, boolean isAotuPageTurning) {
        this.activity = activity;
        this.ll = ll;
        this.viewPager = viewPager;
        this.handler = handler;
        this.what = what;
        this.isAotuPageTurning = isAotuPageTurning;
    }

    public void excu() {
        oldPosition = 0;
        initData();
        stopTimer();
        createPoint(getLength());
    }

    abstract void initData();

    abstract int getLength();

    private void createPoint(int length) {
        if (length > 0) {
            viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
            ll.removeAllViews();
            for (int i = 0; i < length; i++) {
                TextView textView = new TextView(activity);
                textView.setBackgroundResource(R.drawable.dot_normal);
                LinearLayout.LayoutParams imageView_layoutParams = new LinearLayout.LayoutParams(
                        (int) QLScreenUtil.dpToPxInt(activity, 8),
                        (int) QLScreenUtil.dpToPxInt(activity, 8));
                imageView_layoutParams.setMargins(
                        (int) QLScreenUtil.dpToPxInt(activity, 3),
                        (int) QLScreenUtil.dpToPxInt(activity, 3),
                        (int) QLScreenUtil.dpToPxInt(activity, 3),
                        (int) QLScreenUtil.dpToPxInt(activity, 6));
                textView.setLayoutParams(imageView_layoutParams);
                if (i == 0)
                    textView.setBackgroundResource(R.drawable.dot_focused);
                ll.addView(textView);
            }

            if (isAotuPageTurning)
                initTimer();

        }
    }

    private boolean TimerExcu;

    private void initTimer() {
        TimerExcu = true;
        if (timer == null) {
            timer = new Timer();
        } else {
            timer.cancel();
            timer = null;
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (TimerExcu) {
                    Message message = handler.obtainMessage();
                    message.what = what;
                    currentItem++;
                    message.arg1 = currentItem;
//                    message.arg2 = currentItem % getLength();
//                    AppLog.D("currentItem" + currentItem% getLength());
                    handler.sendMessage(message);
                }
            }
        }, 3000, 3000);
    }

    private void stopTimer() {
        currentItem = 0;
        TimerExcu = false;
        if (timer != null) {
            timer.cancel();
        }
    }

    private int oldPosition = 0;

    private class MyOnPageChangeListener implements
            ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            currentItem = i;
            ll.getChildAt(oldPosition % getLength()).setBackgroundResource(
                    R.drawable.dot_normal);
            ll.getChildAt(i % getLength()).setBackgroundResource(
                    R.drawable.dot_focused);
            oldPosition = i;
            Message message = handler.obtainMessage();
            message.what = 7;
            message.arg2 = i % getLength();
            handler.sendMessage(message);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    }


    public void onResume() {
        TimerExcu = true;
    }

    public void onPause() {
        TimerExcu = false;
    }


}