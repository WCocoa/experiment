package com.qiantang.neighbourmother.model;

/**
 * ClassName:社群首页明星达人
 * author: Cocoa
 * date: 2016/12/19.
 */

public class StarObj {

    /**
     * user_id : 33
     * fans : 0
     * user_avatar : 33/yRpecYgYFKjb58Rn79vz1477879216
     * user_name : 若梦、泪倾城、夕阳映雪
     */

    private String user_id;
    private int fans;
    private String user_avatar;
    private String user_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}