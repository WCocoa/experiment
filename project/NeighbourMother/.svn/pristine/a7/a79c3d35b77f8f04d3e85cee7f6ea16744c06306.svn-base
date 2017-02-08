package com.qiantang.neighbourmother.logic;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.qiantang.neighbourmother.util.AppLog;


/**
 * @author quliang
 * @version 2015-9-2 上午9:41:39
 *          desc
 */
public class ListVewTopButton {
    private ImageView return_top;
    private ListView listview;
    private boolean isDoing;
    private boolean show;
    private int duration = 300;
    private MyAnimatorListenerAdapter myAnimatorListenerAdapter;
    private ImageView identity;
    private Activity context;

    public ListVewTopButton(ListView listview, ImageView return_top) {
        this.return_top = return_top;
        this.listview = listview;
        myAnimatorListenerAdapter = new MyAnimatorListenerAdapter();
        init();
    }

    public ListVewTopButton(ListView listview, ImageView return_top, ImageView identity, Activity context) {
        this.return_top = return_top;
        this.listview = listview;
        this.identity = identity;
        this.context = context;
        myAnimatorListenerAdapter = new MyAnimatorListenerAdapter();
        init();
    }

    private void homeIdentity(int scrollState) {
        if (scrollState == 0) {

            identity.setVisibility(View.VISIBLE);
        } else {
            identity.setVisibility(View.INVISIBLE);
        }


    }
    boolean flag = true;
    private void init() {
        return_top.setAlpha(0.8f);
//        return_top.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listview.smoothScrollToPosition(0);
//            }
//        });

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                AppLog.D("scrollState:" + scrollState);
                if (identity != null) {
                    if (scrollState == SCROLL_STATE_TOUCH_SCROLL && flag) {

                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(ObjectAnimator.ofFloat(identity, "alpha", 1, 0.5f), ObjectAnimator.ofFloat(identity, "translationX", 0, 100));
                        set.setDuration(500);
                        set.start();
                        flag = false;
                    } else if (scrollState == SCROLL_STATE_IDLE && !flag) {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AnimatorSet set = new AnimatorSet();
                                set.playTogether(ObjectAnimator.ofFloat(identity, "alpha", 0.5f, 1), ObjectAnimator.ofFloat(identity, "translationX", 100, 0));
                                set.setDuration(500);
                                set.start();
                                flag = true;
                            }
                        }, 1000);

                    }
                }

            }

            @Override
            public void onScroll(AbsListView view, final int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem > 1) {

                    if (return_top.getScaleX() != 1f && !isDoing) {
                        show = false;
                        isDoing = true;
                        return_top.setVisibility(View.VISIBLE);
                        PropertyValuesHolder scX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
                        PropertyValuesHolder scY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
                        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(return_top, scX, scY);
                        objectAnimator.setDuration(duration);
                        objectAnimator.setInterpolator(new AccelerateInterpolator());
                        objectAnimator.addListener(myAnimatorListenerAdapter);
                        objectAnimator.start();
                    }

                } else {

                    if (return_top.getScaleX() != 0f && !isDoing) {
                        show = true;
                        isDoing = true;
                        PropertyValuesHolder scX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f);
                        PropertyValuesHolder scY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f);
                        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(return_top, scX, scY);
                        objectAnimator.setDuration(duration);
                        objectAnimator.setInterpolator(new AccelerateInterpolator());
                        objectAnimator.addListener(myAnimatorListenerAdapter);
                        objectAnimator.start();
                    }
                }


                if (onfirstVisibleItem != null)
                    onfirstVisibleItem.myOnScroll(firstVisibleItem);

            }
        });
    }

    private class MyAnimatorListenerAdapter extends AnimatorListenerAdapter {

        @Override
        public void onAnimationEnd(Animator animation) {
            if (show)
                return_top.setVisibility(View.GONE);
            isDoing = false;
        }
    }


    private OnfirstVisibleItem onfirstVisibleItem;

    public void setOnfirstVisibleItem(OnfirstVisibleItem onfirstVisibleItem) {
        this.onfirstVisibleItem = onfirstVisibleItem;
    }

    public interface OnfirstVisibleItem {
        public void myOnScroll(int firstVisibleItem);
    }

    ;
}