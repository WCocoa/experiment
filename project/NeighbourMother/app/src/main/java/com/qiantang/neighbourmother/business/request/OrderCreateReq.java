package com.qiantang.neighbourmother.business.request;

/**
 * ClassName:登录接口参数对象
 * author: Cocoa
 * date: 2016/8/4.
 */
public class OrderCreateReq extends BaseRequset {
    private String order_id;
    private String servant_id;//	是 	普通 	string 		专员id
    private String service_id;// 	是 	普通 	string 		服务id用逗号连接　或者使用数组　例如: "1,5,6" 或者[1,5,6]
    private String money ;//	是 	普通 	string 		金额
//    private String servant_type_id ;//	是 	普通 	string 		首页4个服务类型
    private String start_date ;//	是 	普通 	string 		开始日期
    private String start_time ;//	是 	普通 	string 		开始时间
    private String place_1 ;//	否 	普通 	string 		接送地点(接)
    private String place_2 ;//	否 	普通 	string 		接送地点(送)
    private String place_3;// 	否 	普通 	string 		看护地点
    private int snack ;//	是 	普通 	string 		加餐数量
    private int tips ;//	是 	普通 	string 		小费
    private double place_1_lat ;//	否 	普通 	string 		接送地点(接)纬度
    private double place_1_lng ;//	否 	普通 	string 		接送地点(接)经度
    private double place_2_lat ;//	否 	普通 	string 		接送地点(送)纬度
    private double place_2_lng ;//	否 	普通 	string 		接送地点(送)经度
    private String order_title ;//	是 	普通 	string 		服务说明汉字
    private String service_phase_id ;//	是 	普通 	string 		学期阶段id
    private int service_price ;//服务价格
    private String order_note ;//服务要求

    public OrderCreateReq() {
    }


    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public int getService_price() {
        return service_price;
    }

    public void setService_price(int service_price) {
        this.service_price = service_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getService_phase_id() {
        return service_phase_id;
    }

    public void setService_phase_id(String service_phase_id) {
        this.service_phase_id = service_phase_id;
    }

    public String getServant_id() {
        return servant_id;
    }

    public void setServant_id(String servant_id) {
        this.servant_id = servant_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

//    public String getServant_type_id() {
//        return servant_type_id;
//    }
//
//    public void setServant_type_id(String servant_type_id) {
//        this.servant_type_id = servant_type_id;
//    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
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

    public int getSnack() {
        return snack;
    }

    public void setSnack(int snack) {
        this.snack = snack;
    }

    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
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

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }
}
