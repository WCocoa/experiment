package com.qiantang.neighbourmother.business.request;

import com.qiantang.neighbourmother.business.response.BaseResp;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class HomeReq extends BaseResp implements Serializable{

    private String district;//	是 	普通 	string 		地区id
    private String community ;//	是 	普通 	string 		小区名称
    private int count=10 ;//	是 	普通 	string 		数量
    private int offset ;//	是 	普通 	string 		起始位置

    public HomeReq() {
    }

    public HomeReq(String district, String community, int count, int offset) {
        this.district = district;
        this.community = community;
        this.count = count;
        this.offset = offset;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
