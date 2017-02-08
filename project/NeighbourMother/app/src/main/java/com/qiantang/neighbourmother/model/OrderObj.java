package com.qiantang.neighbourmother.model;

import java.io.Serializable;

/**
 * Created by quliang on 16-11-8.
 */

public class OrderObj implements Serializable {
    private long     ctime;
    private String   order_id;
    private String   order_title;//	string 		订单内容
    private String[] service_type_id;
    /**
     * 1早上送孩子2晚上接孩子3看护4辅导作业5周末看护
     */
    private int[]    service_cate_id;
    private int      order_star;//打分评价
    private String   order_no;
    private int      additional_money;//	string 		追加金额
    private String   additional_reason;//	string 		追加原因
    private int      additional_state;//	string 		追加金额支付状态　　0 未支付　１已支付　２正在处理
    private int      service_phase_id;//	string 		学期阶段id
    private String   service_id;
    private int      money;//	string 		金额
    private String   start_time;//	string 		开始日期
    private String   start_date;//	string 		开始时间
    private String   place_1;//	string 		接送地点　接
    private String   place_2;//	string 		接送地点　送
    private String   place_3;//string 		看护地点
    private double   place_1_lat;//	否 	普通 	string 		接送地点(接)纬度
    private double   place_1_lng;//	否 	普通 	string 		接送地点(接)经度
    private double   place_2_lat;//	否 	普通 	string 		接送地点(送)纬度
    private double   place_2_lng;//	否 	普通 	string 		接送地点(送)经度
    private int      order_status;//	string 		'0 待确认，1已确认待接单，2已接单待支付，3已支付等待服务开始　4开始服务　5服务完成　6订单取消 7订单失效',
    private int      tips;//	string 		消费
    private int      snack;//	string 		加餐数量
    private int      snack_money;//加餐单价
    private long     current_time;//	string 		现在时间
    private String   servant_id;//	string 		专员id
    private long     accept_time;//	string 		专员接受订单的时间
    private String      order_note;
    private int      countdown_time;
    private int      service_price;//服务价格


    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

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

    private int order_delete_user;//	string		用户删除　１　未删除　０删除
    private int order_delete_servant;//	string		专员删除　１　未删除　０删除
    private int order_state;//	string		是否支付　０未支付　１已支付


    public int getService_price() {
        return service_price;
    }

    public void setService_price(int service_price) {
        this.service_price = service_price;
    }

    public int getCountdown_time() {
        return countdown_time;
    }

    public void setCountdown_time(int countdown_time) {
        this.countdown_time = countdown_time;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String[] getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(String[] service_type_id) {
        this.service_type_id = service_type_id;
    }

    public int[] getService_cate_id() {
        return service_cate_id;
    }

    public void setService_cate_id(int[] service_cate_id) {
        this.service_cate_id = service_cate_id;
    }

    public int getOrder_star() {
        return order_star;
    }

    public void setOrder_star(int order_star) {
        this.order_star = order_star;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getAdditional_money() {
        return additional_money;
    }

    public void setAdditional_money(int additional_money) {
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

    public int getService_phase_id() {
        return service_phase_id;
    }

    public void setService_phase_id(int service_phase_id) {
        this.service_phase_id = service_phase_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public String getPlace_1() {
        return place_1;
    }

    public void setPlace_1(String place_1) {
        this.place_1 = place_1;
    }

    public String getPlace_2() {
        return place_2;
    }

    public void setPlace_2(String place_2) {
        this.place_2 = place_2;
    }

    public String getPlace_3() {
        return place_3;
    }

    public void setPlace_3(String place_3) {
        this.place_3 = place_3;
    }

    public double getPlace_1_lat() {
        return place_1_lat;
    }

    public void setPlace_1_lat(double place_1_lat) {
        this.place_1_lat = place_1_lat;
    }

    public double getPlace_1_lng() {
        return place_1_lng;
    }

    public void setPlace_1_lng(double place_1_lng) {
        this.place_1_lng = place_1_lng;
    }

    public double getPlace_2_lat() {
        return place_2_lat;
    }

    public void setPlace_2_lat(double place_2_lat) {
        this.place_2_lat = place_2_lat;
    }

    public double getPlace_2_lng() {
        return place_2_lng;
    }

    public void setPlace_2_lng(double place_2_lng) {
        this.place_2_lng = place_2_lng;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
    }

    public int getSnack() {
        return snack;
    }

    public void setSnack(int snack) {
        this.snack = snack;
    }

    public int getSnack_money() {
        return snack_money;
    }

    public void setSnack_money(int snack_money) {
        this.snack_money = snack_money;
    }

    public long getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(long current_time) {
        this.current_time = current_time;
    }

    public String getServant_id() {
        return servant_id;
    }

    public void setServant_id(String servant_id) {
        this.servant_id = servant_id;
    }

    public long getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(long accept_time) {
        this.accept_time = accept_time;
    }
}
