package com.qiantang.neighbourmother.business.request;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/12/20.
 */

public class CreateGroupReq extends BaseRequset {

    /**
     * group_pic : 111
     * group_name : 222
     * group_introduce : 333
     */

    private String group_pic;
    private String group_name;
    private String group_introduce;

    public CreateGroupReq(String group_pic, String group_name, String group_introduce) {
        this.group_pic = group_pic;
        this.group_name = group_name;
        this.group_introduce = group_introduce;
    }

    public String getGroup_pic() {
        return group_pic;
    }

    public void setGroup_pic(String group_pic) {
        this.group_pic = group_pic;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_introduce() {
        return group_introduce;
    }

    public void setGroup_introduce(String group_introduce) {
        this.group_introduce = group_introduce;
    }
}
