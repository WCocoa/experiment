package com.cocoa.mvideoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

import com.cocoa.mvideoplayer.utils.MeasureHelper;


/**
 * 用于显示video的，做了横屏与竖屏的匹配，还有需要rotation需求的
 * Created by shuyu on 2016/11/11.
 */

public class VedioTextureView extends TextureView {

    private MeasureHelper measureHelper;

    public VedioTextureView(Context context) {
        super(context);
        init();
    }

    public VedioTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        measureHelper = new MeasureHelper(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int videoWidth = VideoManager.instance().getCurrentVideoWidth();
        int videoHeight = VideoManager.instance().getCurrentVideoHeight();

        int videoSarNum = VideoManager.instance().getMediaPlayer().getVideoSarNum();
        int videoSarDen = VideoManager.instance().getMediaPlayer().getVideoSarDen();

        if (videoWidth > 0 && videoHeight > 0) {
            measureHelper.setVideoSampleAspectRatio(videoSarNum, videoSarDen);
            measureHelper.setVideoSize(videoWidth, videoHeight);
        }
        measureHelper.setVideoRotation((int)getRotation());
        measureHelper.doMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(measureHelper.getMeasuredWidth(), measureHelper.getMeasuredHeight());
    }

    public int getSizeH() {
        return measureHelper.getMeasuredHeight();
    }

    public int getSizeW() {
        return measureHelper.getMeasuredWidth();
    }
}