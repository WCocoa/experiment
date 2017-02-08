package com.qiantang.neighbourmother.business.response;

import java.io.Serializable;

/**
 * ClassName:服务介绍请求接口返回参数
 * author: Cocoa
 * date: 2016/9/29.
 */

public class ServiceIntroduceResp extends BaseResp implements Serializable {


    /**
     * service_phase_id : 1
     * service_phase_name : 学前
     * ctime : 0
     * utime : 0
     * enable : 0
     * content :
     */

    private int service_phase_id;
    private String service_phase_name;
    private String ctime;
    private String utime;
    private int enable;
    private String content;
//    首页点击的item
    private int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getService_phase_id() {
        return service_phase_id;
    }

    public void setService_phase_id(int service_phase_id) {
        this.service_phase_id = service_phase_id;
    }

    public String getService_phase_name() {
        return service_phase_name;
    }

    public void setService_phase_name(String service_phase_name) {
        this.service_phase_name = service_phase_name;
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

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
