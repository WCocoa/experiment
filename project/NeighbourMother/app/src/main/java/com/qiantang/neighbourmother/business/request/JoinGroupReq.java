package com.qiantang.neighbourmother.business.request;

/**
 * ClassName:关注请求参数
 * author: Cocoa
 * date: 2016/12/20.
 */

public class JoinGroupReq {
    private String group_id;

    public JoinGroupReq(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
