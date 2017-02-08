package com.qiantang.neighbourmother.business.response;

import com.qiantang.neighbourmother.model.AdObj;
import com.qiantang.neighbourmother.model.HomeItemChildObj;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/12/7.
 */
public class HomeResp extends BaseResp implements Serializable{
    ArrayList<AdObj>  ad= new ArrayList<AdObj>();
    ArrayList<HomeItemChildObj> list=new ArrayList<HomeItemChildObj>();

    public HomeResp(ArrayList<HomeItemChildObj> list, ArrayList<AdObj> ad) {
        this.list = list;
        this.ad = ad;
    }

    public ArrayList<AdObj> getAd() {
        return ad;
    }

    public void setAd(ArrayList<AdObj> ad) {
        this.ad = ad;
    }

    public ArrayList<HomeItemChildObj> getList() {
        return list;
    }

    public void setList(ArrayList<HomeItemChildObj> list) {
        this.list = list;
    }
}
