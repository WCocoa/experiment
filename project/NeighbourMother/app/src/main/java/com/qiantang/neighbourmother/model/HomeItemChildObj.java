package com.qiantang.neighbourmother.model;

/**
 * Created by quliang on 16-8-4.
 */
public class HomeItemChildObj {
    private String user_id;//	string 		用户id
    private String user_avatar;// 	string 		头像
    private String user_name;// 	string 		姓名
    private int    user_gender;// 	string 		性别
    private String user_age;// 	string 		年龄
    private int    user_star;// 	string 		星级
    private String servant_type_string;// 	array 		专员类型
    private int    user_type;// 	1普通用2专员3店员
    private String user_address;//详细地址

    private String province_name;//	string		省
    private String city_name;//	string		市
    private String district_name;//	string		区
    private String user_community;//小区
    private int    order_number;//接单量
    private String shop_name;//店名
    private String servant_pic;//首页专员图片

    public String getServant_pic() {
        return servant_pic;
    }

    public void setServant_pic(String servant_pic) {
        this.servant_pic = servant_pic;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getUser_community() {
        return user_community;
    }

    public void setUser_community(String user_community) {
        this.user_community = user_community;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getServant_type_string() {
        return servant_type_string;
    }

    public void setServant_type_string(String servant_type_string) {
        this.servant_type_string = servant_type_string;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(int user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public int getUser_star() {
        return user_star;
    }

    public void setUser_star(int user_star) {
        this.user_star = user_star;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
}