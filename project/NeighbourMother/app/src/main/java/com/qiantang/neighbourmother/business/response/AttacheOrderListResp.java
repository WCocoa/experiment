package com.qiantang.neighbourmother.business.response;

/**
 * ClassName:专员订单列表
 * author: Cocoa
 * date: 2016/9/29.
 */

public class AttacheOrderListResp extends BaseResp {

    /**
     * user_id : 3
     * order_no : 1092714480024031
     * order_status : 4
     * order_title :
     * start_time :
     * start_date :
     * money : 3
     * avatar : 2222
     * name : 1111
     */

    private String user_id;
    private String order_no;
    private int    order_status;
    private String order_title;
    private String start_time;
    private String start_date;
    private double money;
    private String avatar;
    private String name;
    private String order_id;
    private String ctime;
    private double additional_money;
    private String additional_reason;
    private int    additional_state;//追加金额状态
    private int order_delete_user;//string		用户删除　１　未删除　０删除
    private int order_delete_servant;//	string		专员删除　１　未删除　０删除
    private int order_state;//	string		是否支付　0 未支付　１已支付

    public int getOrder_delete_user() {
        return order_delete_user;
    }

    public void setOrder_delete_user(int order_delete_user) {
        this.order_delete_user = order_delete_user;
    }

    public int getOrder_delete_servant() {
        return order_delete_servant;
    }

    public void setOrder_delete_servant(int order_delete_servant) {
        this.order_delete_servant = order_delete_servant;
    }

    public int getOrder_state() {
        return order_state;
    }

    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }



    public double getAdditional_money() {
        return additional_money;
    }

    public void setAdditional_money(double additional_money) {
        this.additional_money = additional_money;
    }

    public String getAdditional_reason() {
        return additional_reason;
    }

    public void setAdditional_reason(String additional_reason) {
        this.additional_reason = additional_reason;
    }

    public int getAdditional_state() {
        return additional_state;
    }

    public void setAdditional_state(int additional_state) {
        this.additional_state = additional_state;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
