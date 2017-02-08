package com.qiantang.neighbourmother.business.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class OrderScoreResp extends BaseResp implements Cloneable,Serializable{
    private int order_star;

    public OrderScoreResp(int order_star) {
        this.order_star = order_star;
    }

    public int getOrder_star() {
        return order_star;
    }

    public void setOrder_star(int order_star) {
        this.order_star = order_star;
    }
}
