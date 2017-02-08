package com.qiantang.neighbourmother.business.response;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:专员订单列表
 * author: Cocoa
 * date: 2016/9/29.
 */

public class OrderPayResp extends BaseResp {

   private String appid;//	string 		微信开放平台审核通过的应用APPID
    private String  partnerid ;//	string 		微信支付分配的商户号
    private String  prepayid ;//	string 		微信返回的支付交易会话ID
    @SerializedName("package")
    private String ppackage ;//	string 		暂填写固定值Sign=WXPay
    private String noncestr ;//	string 		随机字符串，不长于32位
    private String   timestamp ;//	string 		时间戳
    private String sign 	;//string 		签名
    private String pay_id ;//	string 		支付id

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPpackage() {
        return ppackage;
    }

    public void setPpackage(String ppackage) {
        this.ppackage = ppackage;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }
}
