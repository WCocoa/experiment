package com.qiantang.neighbourmother.logic;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qiantang.neighbourmother.util.AppLog;

/**
 * ClassName:item间距
 * author: Cocoa
 * date: 2016/12/19.
 */

public class StarDecoration extends RecyclerView.ItemDecoration {
    private int     spanCount;
    private int     spacing;
    private boolean includeEdge;

    public StarDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (includeEdge) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column   = position % spanCount;
            AppLog.D("position" + position);

            if (position % 2 == 0) {

                outRect.top = spacing;
                outRect.left = spacing / 2;
                outRect.right = spacing;

            } else {
                outRect.top = spacing;
                outRect.left = spacing;
                outRect.right = spacing / 2;
            }

            if (parent.getChildPosition(view) == 0) {
                outRect.top = 0;
                outRect.bottom = 0;
            }
        }
    }
}
