package com.qiantang.neighbourmother.model;

/**
 * Created by quliang on 16-12-20.
 */

public class PostDetailObj {

    private String     post_id;
    private int        like;
    private int        comment;
    private LabelObj[] label;
    private String     url;
    private String     share_url;
    private int        liked;

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public LabelObj[] getLabel() {
        return label;
    }

    public void setLabel(LabelObj[] label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
}
