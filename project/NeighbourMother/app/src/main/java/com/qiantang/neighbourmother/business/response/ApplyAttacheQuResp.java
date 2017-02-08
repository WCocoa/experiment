package com.qiantang.neighbourmother.business.response;

import java.io.Serializable;

/**
 * ClassName:登录返回对象
 * author: Cocoa
 * date: 2016/8/4.
 */
public class ApplyAttacheQuResp extends BaseResp implements Serializable{

    private String _id;
    private String user_id;
    private int apply_status;
    private String type;
    private String ctime;
    private String utime;
    private String user_name;
    private int user_gender;
    private String user_age;
    private String user_address;
    private int province;
    private int city;
    private int district;
    private String province_name;
    private String city_name;
    private String district_name;

    private String user_community;
    private String industry;
    private int owner;
    private String education;
    private String pic_standard;
    private String pic_idcard;
    private String pic_environment_1;
    private String pic_environment_2;
    private String pic_environment_3;
    private String pic_teacher;
    private String pic_education;
    private String car_number;
    private String car_model;
    private String car_condition;
    private String car_age;
    private String car_pic;
    private String car_driving;
    private String car_license_number;
    private String user_tel;


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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getApply_status() {
        return apply_status;
    }

    public void setApply_status(int apply_status) {
        this.apply_status = apply_status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
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

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getUser_community() {
        return user_community;
    }

    public void setUser_community(String user_community) {
        this.user_community = user_community;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPic_standard() {
        return pic_standard;
    }

    public void setPic_standard(String pic_standard) {
        this.pic_standard = pic_standard;
    }

    public String getPic_idcard() {
        return pic_idcard;
    }

    public void setPic_idcard(String pic_idcard) {
        this.pic_idcard = pic_idcard;
    }

    public String getPic_environment_1() {
        return pic_environment_1;
    }

    public void setPic_environment_1(String pic_environment_1) {
        this.pic_environment_1 = pic_environment_1;
    }

    public String getPic_environment_2() {
        return pic_environment_2;
    }

    public void setPic_environment_2(String pic_environment_2) {
        this.pic_environment_2 = pic_environment_2;
    }

    public String getPic_environment_3() {
        return pic_environment_3;
    }

    public void setPic_environment_3(String pic_environment_3) {
        this.pic_environment_3 = pic_environment_3;
    }

    public String getPic_teacher() {
        return pic_teacher;
    }

    public void setPic_teacher(String pic_teacher) {
        this.pic_teacher = pic_teacher;
    }

    public String getPic_education() {
        return pic_education;
    }

    public void setPic_education(String pic_education) {
        this.pic_education = pic_education;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getCar_condition() {
        return car_condition;
    }

    public void setCar_condition(String car_condition) {
        this.car_condition = car_condition;
    }

    public String getCar_age() {
        return car_age;
    }

    public void setCar_age(String car_age) {
        this.car_age = car_age;
    }

    public String getCar_pic() {
        return car_pic;
    }

    public void setCar_pic(String car_pic) {
        this.car_pic = car_pic;
    }

    public String getCar_driving() {
        return car_driving;
    }

    public void setCar_driving(String car_driving) {
        this.car_driving = car_driving;
    }

    public String getCar_license_number() {
        return car_license_number;
    }

    public void setCar_license_number(String car_license_number) {
        this.car_license_number = car_license_number;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }
}
