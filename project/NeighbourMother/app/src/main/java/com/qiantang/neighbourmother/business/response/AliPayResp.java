package com.qiantang.neighbourmother.business.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class AliPayResp extends BaseResp implements Cloneable,Serializable{
    private String data;//	孩子id
    private String pay_id ;//	string 		支付id

    public AliPayResp(String data,String pay_id)
    {
        this.data = data;
        this.pay_id = pay_id;
    }


    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
