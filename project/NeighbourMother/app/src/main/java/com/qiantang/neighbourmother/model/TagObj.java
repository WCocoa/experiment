package com.qiantang.neighbourmother.model;

/**
 * Created by quliang on 16-12-20.
 */

public class TagObj {

private String lable_id;
private String name;
private String color;
private boolean Checked;

    public TagObj(String lable_id, String name, String color) {
        this.lable_id = lable_id;
        this.name = name;
        this.color = color;
    }


    public boolean isChecked() {
        return Checked;
    }

    public void setChecked(boolean checked) {
        Checked = checked;
    }

    public String getLable_id() {
        return lable_id;
    }

    public void setLable_id(String lable_id) {
        this.lable_id = lable_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
