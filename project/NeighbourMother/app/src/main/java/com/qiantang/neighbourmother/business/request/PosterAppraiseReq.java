package com.qiantang.neighbourmother.business.request;

import com.qiantang.neighbourmother.business.response.BaseResp;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/7.
 */
public class PosterAppraiseReq extends BaseResp implements Serializable{

    private String post_id;


    public PosterAppraiseReq(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
}
