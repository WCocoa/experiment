package com.qiantang.neighbourmother.model;

import java.io.Serializable;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/8/17.
 * 倒计时时间对象
 */
public class OrderTimeObj implements Serializable {
    private String sTime;
    private boolean isfinish;// 倒计时是否结束;

    public OrderTimeObj() {
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public boolean isfinish() {
        return isfinish;
    }

    public void setIsfinish(boolean isfinish) {
        this.isfinish = isfinish;
    }
}
