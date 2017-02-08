package com.qiantang.neighbourmother.business.request;

/**
 * ClassName:追加费用请求参数
 * author: Cocoa
 * date: 2016/10/9.
 */

public class SuperadditionCostReq extends BaseRequset {
    private String order_id;
    private String reason;

    public SuperadditionCostReq(String order_id, String reason, double money) {
        this.order_id = order_id;
        this.reason = reason;
        this.money = money;
    }

    public SuperadditionCostReq() {

    }

    public double getMoney() {

        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private double money;
}
