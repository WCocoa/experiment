package com.qiantang.neighbourmother.business.request;

/**
 * ClassName:登录接口参数对象
 * author: Cocoa
 * date: 2016/8/4.
 */
public class DiscussionGroupHomeReq extends BaseRequset {

private int count;
private int offset;
private String group_id;


    public DiscussionGroupHomeReq(int count, int offset, String group_id) {
        this.count = count;
        this.offset = offset;
        this.group_id = group_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
