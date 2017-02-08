package com.qiantang.neighbourmother.model;

/**
 * ClassName:服务
 * author: Cocoa
 * date: 2016/9/22.
 */
public class ServiceDetailsContentObj {
    private String desc;
    private boolean status;
    private int id;

    public ServiceDetailsContentObj(String desc, boolean status, int id) {
        this.desc = desc;
        this.status = status;
        this.id = id;
    }

    public ServiceDetailsContentObj() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
