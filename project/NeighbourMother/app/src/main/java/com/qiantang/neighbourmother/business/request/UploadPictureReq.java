package com.qiantang.neighbourmother.business.request;

/**
 * ClassName:上传图片
 * author: Cocoa
 * date: 2016/10/9.
 */

public class UploadPictureReq extends BaseRequset {
    private String order_id;
    private String image_1;
    private String image_2;
    private String image_3;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public UploadPictureReq() {
    }

    public UploadPictureReq(String order_id, String image_1, String image_2, String image_3, String content) {
        this.order_id = order_id;
        this.image_1 = image_1;
        this.image_2 = image_2;
        this.image_3 = image_3;
        this.content = content;
    }

    public String getImage_1() {
        return image_1;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }

    public String getImage_3() {
        return image_3;
    }

    public void setImage_3(String image_3) {
        this.image_3 = image_3;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
}
