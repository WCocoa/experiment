package com.qiantang.neighbourmother.logic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.qiantang.neighbourmother.R;
import com.qiantang.neighbourmother.business.API;
import com.qiantang.neighbourmother.business.APIConfig;
import com.qiantang.neighbourmother.model.AuthObj;
import com.qiantang.neighbourmother.ui.BaseActivity;
import com.qiantang.neighbourmother.ui.activity.PhotoViewActivity;
import com.qiantang.neighbourmother.util.AppLog;
import com.qiantang.neighbourmother.util.IntentFinal;

import java.util.ArrayList;

/**
 * Created by quliang on 16-8-4.
 */
public class AuthUtil {
    private Activity activity;


    private ArrayList<AuthObj> authObjs = new ArrayList<AuthObj>();
    private int currentIndex;

    private TakeImage takeImage;
    private Handler handler;

    public AuthUtil(Activity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    public void add(AuthObj authObj) {
        if (authObj != null)
            authObjs.add(authObj);
    }

    public AuthObj get(int index) {
        return authObjs.get(index);
    }

    public ArrayList<AuthObj> getAuthObjs() {
        return authObjs;
    }

    public void initData() {

        takeImage = new TakeImage(activity, handler, false);
        int authoSize = authObjs.size();
        for (int i = 0; i < authoSize; i++) {
            AuthObj authObj = authObjs.get(i);
            ImageView img_auth = authObj.getImg_auth();
            ImageView img_delete = authObj.getImg_delete();
            String netImgPath = authObj.getNetImgPath();

            img_auth.setOnClickListener(new AuthOnclick(i));
            img_delete.setOnClickListener(new DeleteOnclick(i));

            if (authObj.getHaveImg() == 1) {
                AppLog.D("netImgPath:" + netImgPath);
                ImageLoader.getInstance().displayImage(APIConfig.BASE_IMG_URL + netImgPath, img_auth, ((BaseActivity) activity).getDisplayImageOptions(R.mipmap.auth_photo_image), new NetImgLoadListener(i));
            }
        }

        takeImage.setOnPictureListener(new TakeImage.PictureListener() {
            @Override
            public void pictureCall(final String path) {
                AppLog.D("setOnPictureListener:" + path);
                if (!TextUtils.isEmpty(path)) {
                    final AuthObj authObj = authObjs.get(currentIndex);
                    authObj.setLocImgPath(path);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ImageLoader.getInstance().displayImage(API.locImgPathPrefix + path, authObj.getImg_auth(), ((BaseActivity) activity).getDisplayImageOptions(R.mipmap.auth_photo_image), new LocImgLoadListener(currentIndex));
                        }
                    });
                }
            }
        });
    }


    private class AuthOnclick implements View.OnClickListener {
        private int index;

        AuthOnclick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            AuthObj authObj = authObjs.get(index);
            Intent intent = null;
            switch (authObj.getHaveImg()) {
                case 0:
                    currentIndex = index;
                    takeImage.showDialog();
                    break;
                case 1:
                    intent = new Intent(activity, PhotoViewActivity.class);
                    intent.putExtra(IntentFinal.INTENT_IMGURL_TO_PHOTOVIEWACTIVITY, APIConfig.BASE_IMG_URL + authObj.getNetImgPath());
//                AppLog.D("locImgPathPrefix+locImgPath:"+locImgPathPrefix+authObj.getLocImgPath());
                    activity.startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(activity, PhotoViewActivity.class);
                    intent.putExtra(IntentFinal.INTENT_IMGURL_TO_PHOTOVIEWACTIVITY,
                            API.locImgPathPrefix + authObj.getLocImgPath());
                    AppLog.D("locImgPathPrefix+locImgPath:" + API.locImgPathPrefix + authObj.getLocImgPath());
                    activity.startActivity(intent);
                    break;
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (takeImage != null)
            takeImage.onActivityResult(requestCode, resultCode, data);
    }

    private class DeleteOnclick implements View.OnClickListener {
        private int index;

        DeleteOnclick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            AuthObj authObj = authObjs.get(index);
            authObj.getImg_auth().setImageResource(R.mipmap.auth_photo_image);
            authObj.setHaveImg(0);
            authObj.setLocImgPath(null);
            authObj.getImg_delete().setVisibility(View.GONE);
        }
    }

    private class LocImgLoadListener implements ImageLoadingListener {
        private int index;

        LocImgLoadListener(int index) {
            this.index = index;
        }

        @Override
        public void onLoadingStarted(String s, View view) {

        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason) {
            AppLog.D("Loc_onLoadingFailed_index:" + index);
            authObjs.get(index).setHaveImg(0);
        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
            AppLog.D("Loc_onLoadingComplete_index:" + index);
            AuthObj authObj = authObjs.get(index);
            if (!TextUtils.isEmpty(authObj.getLocImgPath())) {
                authObj.setHaveImg(2);
                authObj.getImg_delete().setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onLoadingCancelled(String s, View view) {

        }
    }

    private class NetImgLoadListener implements ImageLoadingListener {
        private int index;

        NetImgLoadListener(int index) {
            this.index = index;
        }

        @Override
        public void onLoadingStarted(String s, View view) {
            AppLog.D("Net_onLoadingStarted_index_s:" + s);
        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason) {
            AppLog.D("Net_onLoadingFailed_index:" + index);
            AuthObj authObj = authObjs.get(index);

            if (!TextUtils.isEmpty(authObj.getNetImgPath())&&authObj.getStatus()==1) {
                authObj.getImg_delete().setVisibility(View.VISIBLE);
            }


        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//            AppLog.D("Net_onLoadingComplete_index:"+index);
//            AppLog.D("Net_onLoadingComplete_index_s:"+s);
            AuthObj authObj = authObjs.get(index);

            if (!TextUtils.isEmpty(authObj.getNetImgPath())&&authObj.getStatus()==1) {
                    authObj.getImg_delete().setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onLoadingCancelled(String s, View view) {

        }
    }


}
