package com.cocoa.mvideoplayer.video;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.cocoa.mvideoplayer.model.VideoModel;
import com.cocoa.mvideoplayer.video.base.BaseVideoPlayer;
import com.cocoa.mvideoplayer.video.base.VideoPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuyu on 2016/12/20.
 */

public class ListVideoPlayer extends StandardVideoPlayer {

    protected List<VideoModel> mUriList = new ArrayList<>();
    protected int mPlayPosition;

    /**
     * 1.5.0开始加入，如果需要不同布局区分功能，需要重载
     */
    public ListVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public ListVideoPlayer(Context context) {
        super(context);
    }

    public ListVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 设置播放URL
     *
     * @param url           播放url
     * @param cacheWithPlay 是否边播边缓存
     * @return
     */
    public boolean setUp(List<VideoModel> url, boolean cacheWithPlay, int position) {
        mUriList = url;
        mPlayPosition = position;
        VideoModel gsyVideoModel = url.get(position);
        boolean set = setUp(gsyVideoModel.getUrl(), cacheWithPlay, gsyVideoModel.getTitle());
        if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
            mTitleTextView.setText(gsyVideoModel.getTitle());
        }
        return set;
    }

    /**
     * 设置播放URL
     *
     * @param url           播放url
     * @param cacheWithPlay 是否边播边缓存
     * @param cachePath     缓存路径，如果是M3U8或者HLS，请设置为false
     * @return
     */
    public boolean setUp(List<VideoModel> url, boolean cacheWithPlay, int position, File cachePath) {
        mUriList = url;
        mPlayPosition = position;
        VideoModel gsyVideoModel = url.get(position);
        boolean set = setUp(gsyVideoModel.getUrl(), cacheWithPlay, cachePath, gsyVideoModel.getTitle());
        if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
            mTitleTextView.setText(gsyVideoModel.getTitle());
        }
        return set;
    }

    @Override
    public BaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        BaseVideoPlayer gsyBaseVideoPlayer = super.startWindowFullscreen(context, actionBar, statusBar);
        if (gsyBaseVideoPlayer != null) {
            ListVideoPlayer listVideoPlayer = (ListVideoPlayer) gsyBaseVideoPlayer;
            listVideoPlayer.mPlayPosition = mPlayPosition;
            listVideoPlayer.mUriList = mUriList;
            VideoModel gsyVideoModel = mUriList.get(mPlayPosition);
            if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
                listVideoPlayer.mTitleTextView.setText(gsyVideoModel.getTitle());
            }
        }
        return gsyBaseVideoPlayer;
    }

    @Override
    protected void resolveNormalVideoShow(View oldF, ViewGroup vp, VideoPlayer gsyVideoPlayer) {
        if (gsyVideoPlayer != null) {
            ListVideoPlayer listVideoPlayer = (ListVideoPlayer) gsyVideoPlayer;
            mPlayPosition = listVideoPlayer.mPlayPosition;
            mUriList = listVideoPlayer.mUriList;
            VideoModel gsyVideoModel = mUriList.get(mPlayPosition);
            if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
                mTitleTextView.setText(gsyVideoModel.getTitle());
            }
        }
        super.resolveNormalVideoShow(oldF, vp, gsyVideoPlayer);
    }

    @Override
    public void onCompletion() {
        if (mPlayPosition < (mUriList.size() - 1)) {
            return;
        }
        super.onCompletion();
    }

    @Override
    public void onAutoCompletion() {
        if (mPlayPosition < (mUriList.size() - 1)) {
            mPlayPosition++;
            VideoModel gsyVideoModel = mUriList.get(mPlayPosition);
            setUp(gsyVideoModel.getUrl(), mCache, mCachePath, gsyVideoModel.getTitle());
            if (!TextUtils.isEmpty(gsyVideoModel.getTitle())) {
                mTitleTextView.setText(gsyVideoModel.getTitle());
            }
            startPlayLogic();
            return;
        }
        super.onAutoCompletion();
    }

}
