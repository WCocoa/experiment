package com.cocoa.mvideoplayer.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.cocoa.mvideoplayer.PreViewManager;
import com.cocoa.mvideoplayer.R;
import com.cocoa.mvideoplayer.video.base.BaseVideoPlayer;


/**
 * Created by shuyu on 2016/12/10.
 * 进度图小图预览，目前对本地视频还可以，网络视频效果不好，不建议使用
 */

public class CustomVideoPlayer extends StandardVideoPlayer {

    private RelativeLayout mPreviewLayout;

    private TextureView mPreviewTexture;

    //是否因为用户点击
    private boolean mIsFromUser;

    //是否打开滑动预览
    private boolean mOpenPreView;

    private int mPreProgress = -2;

    /**
     * 1.5.0开始加入，如果需要不同布局区分功能，需要重载
     */
    public CustomVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public CustomVideoPlayer(Context context) {
        super(context);
    }

    public CustomVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        initView();
    }

    private void initView() {
        mPreviewLayout = (RelativeLayout) findViewById(R.id.preview_layout);
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_layout_custom;
    }


    @Override
    protected void addTextureView() {
        super.addTextureView();

        if (mPreviewLayout.getChildCount() > 0) {
            mPreviewLayout.removeAllViews();
        }
        mPreviewTexture = null;
        mPreviewTexture = new TextureView(getActivityContext());
        mPreviewTexture.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                PreViewManager.instance().setDisplay(new Surface(surface));
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                PreViewManager.instance().setDisplay(null);
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
        mPreviewTexture.setRotation(mRotate);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mPreviewLayout.addView(mPreviewTexture, layoutParams);
    }

    @Override
    protected void prepareVideo() {
        PreViewManager.instance().prepare(mUrl, mMapHeadData, mLooping, mSpeed);
        super.prepareVideo();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
        super.onProgressChanged(seekBar, progress, fromUser);
        if (fromUser && mOpenPreView) {
            int width = seekBar.getWidth();
            int offset = (int) (width - (getResources().getDimension(R.dimen.seek_bar_image) / 2)) / 100 * progress;

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mPreviewLayout.getLayoutParams();
            layoutParams.leftMargin = offset;
            //设置帧预览图的显示位置
            mPreviewLayout.setLayoutParams(layoutParams);
            if (PreViewManager.instance().getMediaPlayer() != null
                    && mHadPlay && (mOpenPreView)
                    && PreViewManager.instance().isSeekToComplete()) {
                PreViewManager.instance().setSeekToComplete(false);
                int time = progress * getDuration() / 100;
                PreViewManager.instance().getMediaPlayer().seekTo(time);
                mPreProgress = progress;
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        super.onStartTrackingTouch(seekBar);
        if (mOpenPreView) {
            mIsFromUser = true;
            mPreviewLayout.setVisibility(VISIBLE);
            mPreProgress = -2;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mOpenPreView) {
            if (mPreProgress >= 0) {
                seekBar.setProgress(mPreProgress);
            }
            super.onStopTrackingTouch(seekBar);
            mIsFromUser = false;
            mPreviewLayout.setVisibility(GONE);
        } else {
            super.onStopTrackingTouch(seekBar);
        }
    }

    @Override
    protected void setTextAndProgress(int secProgress) {
        if (mIsFromUser) {
            return;
        }
        super.setTextAndProgress(secProgress);
    }

    @Override
    public BaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        BaseVideoPlayer gsyBaseVideoPlayer   = super.startWindowFullscreen(context, actionBar, statusBar);
        CustomVideoPlayer  customVideoPlayer = (CustomVideoPlayer) gsyBaseVideoPlayer;
        customVideoPlayer.mOpenPreView = mOpenPreView;
        return gsyBaseVideoPlayer;
    }

    public boolean isOpenPreView() {
        return mOpenPreView;
    }

    /**
     * 如果是需要进度条预览的设置打开，默认关闭
     */
    public void setOpenPreView(boolean localFile) {
        this.mOpenPreView = localFile;
    }
}
