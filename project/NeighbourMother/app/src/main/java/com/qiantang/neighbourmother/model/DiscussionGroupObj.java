package com.qiantang.neighbourmother.model;

/**
 * Created by quliang on 16-8-4.
 */
public class DiscussionGroupObj {


    /**
     * group_id : 2
     * group_pic : 111
     * group_name : 222
     * group_introduce : 333
     * join : 0
     */

    private String group_id;
    private String group_pic;
    private String group_name;
    private String group_introduce;
    private int    join;

    public DiscussionGroupObj() {
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
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

    public int getJoin() {
        return join;
    }

    public void setJoin(int join) {
        this.join = join;
    }
}
