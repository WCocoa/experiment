package com.qiantang.neighbourmother.business.request;

import com.qiantang.neighbourmother.business.response.BaseResp;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class UserAddressSubReq extends BaseResp implements Serializable{

    private int province;// 	string 		省份id
    private int city ;//	string 		城市id
    private int district ;//	string 		地址id
    private String user_community ;//	string 		小区

    public UserAddressSubReq() {
    }

    public UserAddressSubReq(int province, int city, int district, String user_community) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.user_community = user_community;
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
}
