package com.qiantang.neighbourmother.business.request;

import com.qiantang.neighbourmother.business.response.BaseResp;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class OrderScoreReq extends BaseResp implements Serializable{

    private String order_id ;//	是 	普通 	string 		订单id
    private String order_star  ;//	是 	普通 	string 		分数

    public OrderScoreReq() {
    }

    public OrderScoreReq(String order_id, String order_star) {
        this.order_id = order_id;
        this.order_star = order_star;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_star() {
        return order_star;
    }

    public void setOrder_star(String order_star) {
        this.order_star = order_star;
    }
}
