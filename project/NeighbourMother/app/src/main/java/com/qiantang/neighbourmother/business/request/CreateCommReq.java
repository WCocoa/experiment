package com.qiantang.neighbourmother.business.request;

/**
 * ClassName:登录接口参数对象
 * author: Cocoa
 * date: 2016/8/4.
 */
public class CreateCommReq extends BaseRequset {

private String post_id;
private String content;



    public String getPostId() {
        return post_id;
    }

    public void setPostId(String postId) {
        this.post_id = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CreateCommReq(String postId, String content) {


        this.post_id = postId;
        this.content = content;
    }
}
