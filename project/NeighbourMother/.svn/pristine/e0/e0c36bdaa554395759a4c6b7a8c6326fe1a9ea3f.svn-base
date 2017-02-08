package com.qiantang.neighbourmother.business.request;

import com.qiantang.neighbourmother.business.response.BaseResp;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class RechargeReq extends BaseResp implements Serializable{

    private int money;
    /**充值类型，1，选择金额　２：输入金额*/
    private int type;
    /**支付方式　１微信　２支付宝*/
    private int pay_platform;

    public RechargeReq() {
    }

    public RechargeReq(int money, int type,int pay_platform) {
        this.money = money;
        this.type = type;
        this.pay_platform = pay_platform;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPay_platform() {
        return pay_platform;
    }

    public void setPay_platform(int pay_platform) {
        this.pay_platform = pay_platform;
    }
}
