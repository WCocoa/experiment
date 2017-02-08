package com.qiantang.neighbourmother.model;

import android.widget.ImageView;

/**
 * Created by quliang on 16-8-4.
 */
public class AuthObj {
        private ImageView img_auth;
    private ImageView img_delete;
//    0未上传照片1获取网络照片2显示本地照片
private int haveImg=1;
//    是否显示delete 1是0否
private int status=1;
    private String locImgPath;
    private String netImgPath;

    public AuthObj(ImageView img_auth, ImageView img_delete, int haveImg, String locImgPath, String netImgPath) {
        this.img_auth = img_auth;
        this.img_delete = img_delete;
        this.haveImg = haveImg;
        this.locImgPath = locImgPath;
        this.netImgPath = netImgPath;
    }

    public ImageView getImg_auth() {
        return img_auth;
    }

    public void setImg_auth(ImageView img_auth) {
        this.img_auth = img_auth;
    }

    public ImageView getImg_delete() {
        return img_delete;
    }

    public void setImg_delete(ImageView img_delete) {
        this.img_delete = img_delete;
    }

    public int getHaveImg() {
        return haveImg;
    }

    public void setHaveImg(int haveImg) {
        this.haveImg = haveImg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocImgPath() {
        return locImgPath;
    }

    public void setLocImgPath(String locImgPath) {
        this.locImgPath = locImgPath;
    }

    public String getNetImgPath() {
        return netImgPath;
    }

    public void setNetImgPath(String netImgPath) {
        this.netImgPath = netImgPath;
    }
}
