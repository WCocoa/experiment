package com.cocoa.mvideoplayer.video.base;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 兼容的空View
 * Created by shuyu on 2016/11/11.
 */

public abstract class VideoPlayer extends BaseVideoPlayer {

    public VideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public VideoPlayer(Context context) {
        super(context);
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}