package com.qiantang.neighbourmother.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ClassName:固定高度的listview
 * author: Cocoa
 * date: 2016/9/22.
 */
public class FixationHeightListView extends ListView {
    public FixationHeightListView(Context context) {
        super(context);
    }


    public FixationHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FixationHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
