package com.qiantang.neighbourmother.business.request;

/**
 * ClassName:登录接口参数对象
 * author: Cocoa
 * date: 2016/8/4.
 */
public class LoginReq extends BaseRequset {
    private String phone;
    private String code;

    public LoginReq() {
    }

    public LoginReq(String phone,String code) {
        this.phone = phone;
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
