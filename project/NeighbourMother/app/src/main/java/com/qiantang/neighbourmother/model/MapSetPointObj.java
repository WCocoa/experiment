package com.qiantang.neighbourmother.model;

import java.io.Serializable;

/**
 * ClassName:经纬度
 * author: Cocoa
 * date: 2016/8/10.
 */
public class MapSetPointObj implements Serializable {

    private double latutide;
    private double longitude;

    public MapSetPointObj() {
    }

    public MapSetPointObj(double latutide, double longitude) {
        this.latutide = latutide;
        this.longitude = longitude;
    }

    public double getLatutide() {
        return latutide;
    }

    public void setLatutide(double latutide) {
        this.latutide = latutide;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
