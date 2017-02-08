package com.qiantang.neighbourmother.logic;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.QLScreenUtil;

/**
 * @author quliang
 * @version 2015-9-2 上午9:41:39
 *          desc 创建标签文本
 */
public class HomeViewPagerPointTurn {

    private Context context;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private  int count;
    private int currentItem;

    public HomeViewPagerPointTurn(ViewPager viewPager, Context context, LinearLayout linearLayout, int count) {
        this.context=context;
        this.linearLayout=linearLayout;
        this.count=count;
        this.viewPager=viewPager;
        createPoint();
    }

    private void createPoint(){
        linearLayout.removeAllViews();
        for (int i = 0; i < count; i++) {
            TextView textView = new TextView(context);
            textView.setBackgroundResource(R.drawable.dot_normal);
            LinearLayout.LayoutParams imageView_layoutParams = new LinearLayout.LayoutParams(
                    (int) QLScreenUtil.dpToPxInt(context, 8),
                    (int)QLScreenUtil.dpToPxInt(context, 8));
            imageView_layoutParams.setMargins(
                    (int) QLScreenUtil.dpToPxInt(context, 3),
                    (int) QLScreenUtil.dpToPxInt(context, 3),
                    (int) QLScreenUtil.dpToPxInt(context, 3),
                    (int) QLScreenUtil.dpToPxInt(context, 6));
            textView.setLayoutParams(imageView_layoutParams);
            if (i == 0)
                textView.setBackgroundResource(R.drawable.dot_focused);
            linearLayout.addView(textView);
        }
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private class MyOnPageChangeListener implements
            ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {

            currentItem = i;
            linearLayout.getChildAt(oldPosition % count).setBackgroundResource(
                    R.drawable.dot_normal);
            linearLayout.getChildAt(i % count).setBackgroundResource(
                    R.drawable.dot_focused);
            oldPosition = i;
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    }
}