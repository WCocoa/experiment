package com.qiantang.neighbourmother.business.request;

import com.qiantang.neighbourmother.model.PublicPosterItemObj;

import java.util.ArrayList;

/**
 * ClassName:登录接口参数对象
 * author: Cocoa
 * date: 2016/8/4.
 */
public class PublicPosterReq extends BaseRequset {
private String title;
private String group_id;
private String label;
private ArrayList<PublicPosterItemObj> content;


    public PublicPosterReq() {
    }

    public PublicPosterReq(String title, String group_id, String label, ArrayList<PublicPosterItemObj> content) {
        this.title = title;
        this.group_id = group_id;
        this.label = label;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<PublicPosterItemObj> getContent() {
        return content;
    }

    public void setContent(ArrayList<PublicPosterItemObj> content) {
        this.content = content;
    }
}
