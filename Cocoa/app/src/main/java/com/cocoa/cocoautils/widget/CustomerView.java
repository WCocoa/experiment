package com.cocoa.cocoautils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/11/15.
 */

public class CustomerView extends View {
    public CustomerView(Context context) {
        super(context);
    }

    public CustomerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int defalutSize = 150;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width  = getSize(defalutSize, widthMeasureSpec);
        int height = getSize(defalutSize, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        int width  = getMeasuredWidth();
        int height = getMeasuredHeight();

        int r = height / 2-4;
        int cx = r;
        int cy =  r;
        paint.setStyle(Paint.Style.FILL);//充满
        paint.setColor(Color.BLUE);
        int left = width * 2;
        paint.setAntiAlias(true);// 设置画笔的锯齿效果
        RectF oval3 = new RectF(r, 0, left+2*36, height);// 设置个新的长方形
        canvas.drawRoundRect(oval3, r, r, paint);//第二个参数是x半径，第三个参数是y半径
        paint.reset();
        paint.setStrokeWidth(8);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx, cy, r, paint);
        paint.reset();
        paint.setColor(Color.RED);



        canvas.drawCircle(cx, cy, r, paint);
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setTextSize(36);
        paint.setTextAlign(Paint.Align.CENTER);


//        int textx = r/2;
        canvas.drawText("V", cx, cy+15, paint);


        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setTextSize(36);
        paint.setStrokeWidth(2);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("会员", height + r, cy+15, paint);


    }

    private int getSize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }
}
