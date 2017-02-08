package com.qiantang.neighbourmother.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.util.AppLog;

import java.math.BigDecimal;

/**
 * Created by hedge_hog on 2015/6/11.
 * <p>
 * add halfstar show
 * <p>
 * Correction clickEvent from Xml
 */
public class RatingBar extends LinearLayout {
    private boolean mClickable;
    private int starCount;
    private OnRatingChangeListener onRatingChangeListener;
    private float starImageSize;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private Drawable starHalfDrawable;
    private int ratingpadding;
    private int ratingsize;



    public void setStarHalfDrawable(Drawable starHalfDrawable) {
        this.starHalfDrawable = starHalfDrawable;
    }

    public void setRatingsize(int ratingsize) {
        this.ratingsize = ratingsize;
    }

    public void setRatingPadding(int ratingpadding) {
        this.ratingpadding = ratingpadding;
    }

    public void setOnRatingChangeListener(
            OnRatingChangeListener onRatingChangeListener) {
        this.onRatingChangeListener = onRatingChangeListener;
    }

    public void setmClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }

    /**
     * @param context
     * @param attrs
     */
    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RatingBar);
//        AppLog.D("starImageSize:" + mTypedArray.getDimension(
//                R.styleable.RatingBar_starImageSize, 20));
        starImageSize = mTypedArray.getDimension(
                R.styleable.RatingBar_starImageSize, 20);
        starCount = mTypedArray.getInteger(R.styleable.RatingBar_starCount, 5);
        starEmptyDrawable = mTypedArray
                .getDrawable(R.styleable.RatingBar_starEmpty);
        ratingsize = mTypedArray.getInteger(R.styleable.RatingBar_ratingsize, 1);
        starFillDrawable = mTypedArray
                .getDrawable(R.styleable.RatingBar_starFill);
        ratingpadding = mTypedArray.getInteger(R.styleable.RatingBar_ratingpadding, 2);

//        AppLog.D("RatingBars_ratingpadding:" + mTypedArray.getInteger(
//                R.styleable.RatingBar_ratingpadding, 2));
        mClickable = mTypedArray.getBoolean(R.styleable.RatingBar_clickable,
                true);
        for (int i = 0; i < starCount; ++i) {
            ImageView imageView = getStarImageView(context, attrs, i);

            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickable) {
                        setStar(indexOfChild(v) + 1);
                        if (onRatingChangeListener != null) {
                            onRatingChangeListener
                                    .onRatingChange(indexOfChild(v) + 1);
                        }
                    }

                }
            });
            addView(imageView);
        }
    }

    /**
     * @param context
     * @param attrs
     * @return
     */
    private ImageView getStarImageView(Context context, AttributeSet attrs,
                                       int starCount) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(
                Math.round(starImageSize), Math.round(starImageSize));

        imageView.setLayoutParams(para);
        imageView.setPadding(0, 0, ratingpadding, 0);


        if (ratingsize > starCount) {
            imageView.setImageDrawable(starFillDrawable);
        } else {
            imageView.setImageDrawable(starEmptyDrawable);
        }
        imageView.setMaxWidth(10);
        imageView.setMaxHeight(10);
        return imageView;

    }

    /**
     * setting start
     *
     * @param starCount
     */

    public void setStar(float starCount) {

        // 浮点数的整数部分
        int fint = (int) starCount;
        BigDecimal b1 = new BigDecimal(Float.toString(starCount));
        BigDecimal b2 = new BigDecimal(Integer.toString(fint));
        // 浮点数的小数部分
        float fPoint = b1.subtract(b2).floatValue();

        starCount = fint > this.starCount ? this.starCount : fint;
        starCount = starCount < 0 ? 0 : starCount;

        // drawfullstar
        for (int i = 0; i < starCount; ++i) {
            ((ImageView) getChildAt(i)).setImageDrawable(starFillDrawable);
        }

        // drawhalfstar
        if (fPoint > 0) {
            ((ImageView) getChildAt(fint)).setImageDrawable(starHalfDrawable);

            // drawemptystar
            for (int i = this.starCount - 1; i >= starCount + 1; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }

        } else {
            // drawemptystar
            for (int i = this.starCount - 1; i >= starCount; --i) {
                ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
            }

        }

    }

    /**
     * change stat listener
     */
    public interface OnRatingChangeListener {

        void onRatingChange(int RatingCount);

    }

}
