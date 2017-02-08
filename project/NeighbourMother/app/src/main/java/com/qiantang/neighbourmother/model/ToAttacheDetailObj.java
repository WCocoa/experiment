package com.qiantang.neighbourmother.model;

import java.io.Serializable;

/**
 * ClassName:服务
 * author: Cocoa
 * date: 2016/9/22.
 */
public class ToAttacheDetailObj implements Serializable{

/**0from attacheList 1form homeList*/
    private int type;
    private String id;

    public ToAttacheDetailObj(String id,int type) {
        this.id = id;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
