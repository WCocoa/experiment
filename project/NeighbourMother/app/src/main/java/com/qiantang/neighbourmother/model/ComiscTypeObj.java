package com.qiantang.neighbourmother.model;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.io.Serializable;

/**
 * 招募专员页面服务类型对象
 */
public class ComiscTypeObj implements Serializable{

    private String title;
    private View view;


    public ComiscTypeObj(String title, View view) {
        this.title = title;
        this.view = view;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
