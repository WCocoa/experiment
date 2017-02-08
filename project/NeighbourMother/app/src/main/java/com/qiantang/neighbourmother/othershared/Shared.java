package com.qiantang.neighbourmother.othershared;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.util.FileFinal;
import com.qiantang.neighbourmother.util.ToastUtils;
import com.qiantang.neighbourmother.wxapi.WXEntryActivity;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class Shared {

    private Activity activity;
    private IWXAPI api;
    private static Tencent mTencent;
    private ShareObj shareObj;

    public Shared(Activity activity, ShareObj shareObj) {
        this.activity = activity;
        this.shareObj = shareObj;
        initWeixin();
        initQQ();
    }

    private void initWeixin() {
        api = WXAPIFactory.createWXAPI(activity, OtherLoginContacts.WEIXIN_APP_ID);
    }

    public void weixinShared(boolean isTimeLine) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = shareObj.getUrl();
//        webpage.webpageUrl = "https://www.hao123.com";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = shareObj.getTitle();
        msg.description = shareObj.getSummary();
        Bitmap thumb = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.shared_icon);
        msg.thumbData = Util.bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = WXEntryActivity.TRANSACTION_SHARED;
        req.message = msg;

        req.scene = isTimeLine ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }


    public void initQQ() {
        if (mTencent == null) {
            mTencent = Tencent.createInstance(OtherLoginContacts.QQ_APP_ID, activity);
        }
    }

    public void qqShared() {
        final Bundle params = new Bundle();
        int shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
        int mExtarFlag = QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE;

        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, mExtarFlag);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareObj.getTitle());
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareObj.getUrl());
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareObj.getSummary());

//        http://img3.douban.com/lpic/s3635685.jpg
//        http://douban.fm/?start=8508g3c27g-3&amp;cid=-3

        saveBitmap();
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, FileFinal.PATH_TEMP + FileFinal.SHARE_APP_ICON);
        // QQ分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if (null != mTencent) {
                    mTencent.shareToQQ(activity, params, qqShareListener);
                }
            }
        });
    }

    IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {
            ToastUtils.toastLong(activity,activity.getResources().getString(R.string.shared_cancer));
        }

        @Override
        public void onComplete(Object response) {
            ToastUtils.toastLong(activity, activity.getResources().getString(R.string.shared_success));
        }

        @Override
        public void onError(UiError e) {
            ToastUtils.toastLong(activity, activity.getResources().getString(R.string.shared_failure));
        }
    };

    public void zoneShared() {
        //QZone分享， SHARE_TO_QQ_TYPE_DEFAULT 图文，SHARE_TO_QQ_TYPE_IMAGE 纯图
        int shareType = QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT;
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, shareType);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, shareObj.getTitle());
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, shareObj.getSummary());
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, shareObj.getUrl());
        // 支持传多个imageUrl
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add(API.APP_LOGO);
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);

        // QZone分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if (null != mTencent) {
                    mTencent.shareToQzone(activity, params, qqShareListener);
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        AppLog.D("onActivityResultonActivityResult——Shared：");
        if (requestCode == Constants.REQUEST_QZONE_SHARE||requestCode == Constants.REQUEST_QQ_SHARE) {
            Tencent.onActivityResultData(requestCode, resultCode, data, qqShareListener);
        };
    }

    private void saveBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.shared_icon);
        File file = new File(FileFinal.PATH_TEMP + FileFinal.SHARE_APP_ICON);
        if (!file.exists()) {
            FileOutputStream out;
            try {
                out = new FileOutputStream(file);
                if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
