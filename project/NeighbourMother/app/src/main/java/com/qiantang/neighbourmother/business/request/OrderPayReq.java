package com.qiantang.neighbourmother.business.request;

import com.qiantang.neighbourmother.business.response.BaseResp;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class OrderPayReq extends BaseResp implements Serializable{

    private String order_id;
    private int pay_type;
    private int pay_platform;


    public OrderPayReq() {
    }

    public OrderPayReq(String order_id,int pay_type) {
        this.order_id = order_id;
        this.pay_type = pay_type;
    }

    public int getPay_platform() {
        return pay_platform;
    }

    public void setPay_platform(int pay_platform) {
        this.pay_platform = pay_platform;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
