package com.qiantang.neighbourmother.model;

/**
 * ClassName:社群用户详情
 * author: Cocoa
 * date: 2016/12/20.
 */

public class ConmmunityUserObj {

    /**
     * follow : 1
     * fans : 0
     * user_gender : 1
     * user_avatar : UUgaPFN76LHTb2DaLl241481708852
     * post : 17
     * user_name : 若梦、泪倾城、夕阳映雪
     * star : 1
     * followed : 0
     */

    private String follow;
    private int fans;
    private int user_gender;
    private String user_avatar;
    private int post;
    private String user_name;
    private int    star;
    private int    followed;

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(int user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }
}
