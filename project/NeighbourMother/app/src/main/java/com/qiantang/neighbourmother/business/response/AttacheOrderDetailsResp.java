package com.qiantang.neighbourmother.business.response;

/**
 * ClassName:专员订单详情
 * author: Cocoa
 * date: 2016/10/8.
 */

public class AttacheOrderDetailsResp extends BaseResp {

    /**
     * order_title :
     * user_id : 3
     * money : 3
     * start_time :
     * start_date :
     * place_1 :
     * place_2 :
     * place_3 :
     * order_status : 4
     * tips :
     * snack :
     * order_additional_money : 2
     * child_id : 2
     * child_name : 1111
     * child_gender : 127
     * child_avatar : 2222
     * child_age : 0
     * child_school : 1111
     * child_phone : 1111
     * child_grade : 1111
     * <p>
     * child_class : 1111
     */
    private String order_note;

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    private int[]  servant_id;
    private int[]  service_cate_id;
    private double place_1_lat;
    private double place_1_lng;
    private double place_2_lat;
    private double place_2_lng;
    private double snack_money;
    private double additional_money;
    private String additional_reason;
    private int    additional_state;
    private String order_title;
    private String user_id;
    private double money;
    private String start_time;
    private String start_date;
    private String place_1;
    private String place_2;
    private String place_3;
    private int    order_status;
    private double tips;
    private int    snack;
    private double order_additional_money;
    private String child_id;
    private String child_name;
    private int    child_gender;
    private String child_avatar;
    private int child_age;
    private String child_school;
    private String child_phone;
    private String child_grade;
    private String child_class;
    private int    order_delete_user;//	string		用户删除　１未删除　０删除
    private int    order_delete_servant;//	string		专员删除　１未删除　０删除
    private int    order_state;//	string		是否支付０未支付　１已经支付

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


    public int[] getServant_id() {
        return servant_id;
    }

    public void setServant_id(int[] servant_id) {
        this.servant_id = servant_id;
    }


    public int[] getService_cate_id() {
        return service_cate_id;
    }

    public void setService_cate_id(int[] service_cate_id) {
        this.service_cate_id = service_cate_id;
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

    public double getSnack_money() {
        return snack_money;
    }

    public void setSnack_money(double snack_money) {
        this.snack_money = snack_money;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
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

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public double getTips() {
        return tips;
    }

    public void setTips(double tips) {
        this.tips = tips;
    }

    public int getSnack() {
        return snack;
    }

    public void setSnack(int snack) {
        this.snack = snack;
    }

    public double getOrder_additional_money() {
        return order_additional_money;
    }

    public void setOrder_additional_money(double order_additional_money) {
        this.order_additional_money = order_additional_money;
    }

    public String getChild_id() {
        return child_id;
    }

    public void setChild_id(String child_id) {
        this.child_id = child_id;
    }

    public String getChild_name() {
        return child_name;
    }

    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }

    public int getChild_gender() {
        return child_gender;
    }

    public void setChild_gender(int child_gender) {
        this.child_gender = child_gender;
    }

    public String getChild_avatar() {
        return child_avatar;
    }

    public void setChild_avatar(String child_avatar) {
        this.child_avatar = child_avatar;
    }

    public int getChild_age() {
        return child_age;
    }

    public void setChild_age(int child_age) {
        this.child_age = child_age;
    }

    public String getChild_school() {
        return child_school;
    }

    public void setChild_school(String child_school) {
        this.child_school = child_school;
    }

    public String getChild_phone() {
        return child_phone;
    }

    public void setChild_phone(String child_phone) {
        this.child_phone = child_phone;
    }

    public String getChild_grade() {
        return child_grade;
    }

    public void setChild_grade(String child_grade) {
        this.child_grade = child_grade;
    }

    public String getChild_class() {
        return child_class;
    }

    public void setChild_class(String child_class) {
        this.child_class = child_class;
    }
}
