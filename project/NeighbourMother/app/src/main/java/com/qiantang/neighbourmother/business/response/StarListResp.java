package com.qiantang.neighbourmother.business.response;

/**
 * ClassName:明星达人列表
 * author: Cocoa
 * date: 2016/12/19.
 */

public class StarListResp extends BaseResp {

    /**
     * user_name : 若梦、泪倾城、夕阳映雪
     * fans : 0
     * user_avatar : 33/yRpecYgYFKjb58Rn79vz1477879216
     * user_id :
     */

    private String user_name;
    private String fans;
    private String user_avatar;
    private String user_id;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
