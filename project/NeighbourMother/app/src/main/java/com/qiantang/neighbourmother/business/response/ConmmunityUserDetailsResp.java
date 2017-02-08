package com.qiantang.neighbourmother.business.response;

import com.qiantang.neighbourmother.model.ConmmunityUserObj;
import com.qiantang.neighbourmother.model.PostObj;

import java.util.List;

/**
 * ClassName：用户详情
 * author: Cocoa
 * date: 2016/12/20.
 */

public class ConmmunityUserDetailsResp extends BaseResp {
    private ConmmunityUserObj user;
    private List<PostObj>     post;

    public ConmmunityUserObj getUser() {
        return user;
    }

    public void setUser(ConmmunityUserObj user) {
        this.user = user;
    }

    public List<PostObj> getPost() {
        return post;
    }

    public void setPost(List<PostObj> post) {
        this.post = post;
    }
}
