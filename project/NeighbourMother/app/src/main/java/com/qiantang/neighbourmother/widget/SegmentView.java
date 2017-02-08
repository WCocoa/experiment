package com.qiantang.neighbourmother.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.QLScreenUtil;


/**
 * ClassName:
 * author: Cocoa
 * date: 2016/7/26.
 */
public class SegmentView extends LinearLayout {
    private TextView textView1;
    private TextView textView2;
    private onSegmentViewClickListener listener;
    private Context context;

    public SegmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        this.setBackgroundResource(R.drawable.seg_view_backgroud);
        init();
    }

    public SegmentView(Context context) {
        super(context);
        this.context = context;
//        this.setBackgroundResource(R.drawable.seg_view_backgroud);
        init();
    }

    private void init() {

        //      this.setLayoutParams(new LinearLayout.LayoutParams(dp2Px(getContext(), 60), LinearLayout.LayoutParams.WRAP_CONTENT));
        textView1 = new TextView(getContext());
        textView2 = new TextView(getContext());
        LayoutParams layoutParams1=new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        LayoutParams layoutParams2=new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        layoutParams1.setMargins(0,0,(int) QLScreenUtil.dpToPx(context,-1),0);
        layoutParams2.setMargins((int)QLScreenUtil.dpToPx(context,-1),0,0,0);

        textView1.setLayoutParams(layoutParams1);
        textView2.setLayoutParams(layoutParams2);
        textView1.setText("选项1");
        textView2.setText("选项2");

            textView2.setTextColor(getResources().getColor(R.color.white));
            textView1.setTextColor(getResources().getColor(R.color.app_title_bg));
        textView1.setMinWidth((int) QLScreenUtil.dpToPx(context,60));
        textView2.setMinWidth((int) QLScreenUtil.dpToPx(context,60));
        textView1.setGravity(Gravity.CENTER);
        textView2.setGravity(Gravity.CENTER);
        textView1.setPadding((int) QLScreenUtil.dpToPx(context,8), (int) QLScreenUtil.dpToPx(context,6), (int) QLScreenUtil.dpToPx(context,8), (int) QLScreenUtil.dpToPx(context,6));
        textView2.setPadding((int) QLScreenUtil.dpToPx(context,8), (int) QLScreenUtil.dpToPx(context,6), (int) QLScreenUtil.dpToPx(context,8), (int) QLScreenUtil.dpToPx(context,6));
        setSegmentTextSize(16);
        textView1.setBackgroundResource(R.drawable.seg_left);
        textView2.setBackgroundResource(R.drawable.seg_right);
        textView1.setSelected(true);

        this.removeAllViews();

        this.addView(textView1);
        this.addView(textView2);

        this.invalidate();

        textView1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (textView1.isSelected()) {
                    return;
                }
                textView1.setSelected(true);
                textView2.setSelected(false);

                textView1.setTextColor(getResources().getColor(R.color.app_title_bg));
                textView2.setTextColor(getResources().getColor(R.color.white));
                if (listener != null) {
                    listener.onSegmentViewClick(textView1, 0);
                }
            }
        });
        textView2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (textView2.isSelected()) {
                    return;
                }
                textView2.setSelected(true);
                textView1.setSelected(false);
                textView2.setTextColor(getResources().getColor(R.color.app_title_bg));
                textView1.setTextColor(getResources().getColor(R.color.white));
                if (listener != null) {
                    listener.onSegmentViewClick(textView2, 1);
                }
            }
        });
    }

    public void setSegmentTextSize(int dp) {
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dp);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dp);
    }

    private static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public void setOnSegmentViewClickListener(onSegmentViewClickListener listener) {
        this.listener = listener;
    }

    public void setSegmentText(CharSequence text, int position) {
        if (position == 0) {
            textView1.setText(text);
        }
        if (position == 1) {
            textView2.setText(text);
        }
    }

    public static interface onSegmentViewClickListener {

        public void onSegmentViewClick(View v, int position);
    }

}
