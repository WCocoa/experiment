package com.qiantang.neighbourmother.model;

import android.widget.ImageView;

/**
 * Created by quliang on 16-8-4.
 */
public class DiscussionUserObj {
     private String user_id;
     private String user_avatar;

    public DiscussionUserObj(String user_id, String user_avatar) {
        this.user_id = user_id;
        this.user_avatar = user_avatar;
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
