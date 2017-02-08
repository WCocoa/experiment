package com.qiantang.neighbourmother.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context) {
        super(context);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollListener != null) {
            scrollListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    private MyScrollListener scrollListener;

    public void setOnMyScrollListener(MyScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public interface MyScrollListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }


}
