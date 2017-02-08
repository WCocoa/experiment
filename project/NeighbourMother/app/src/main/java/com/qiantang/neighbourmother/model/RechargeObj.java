package com.qiantang.neighbourmother.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by next on 2016/3/24.
 */
public class RechargeObj implements Serializable {

    private String id;
    private int money;
    @SerializedName("for")
    private int give;
    private boolean selected;

    public RechargeObj() {
    }

    public RechargeObj(String id, int money, int give) {
        this.id = id;
        this.money = money;
        this.give = give;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGive() {
        return give;
    }

    public void setGive(int give) {
        this.give = give;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
