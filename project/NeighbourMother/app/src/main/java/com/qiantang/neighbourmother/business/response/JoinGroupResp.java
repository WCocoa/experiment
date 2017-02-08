package com.qiantang.neighbourmother.business.response;

/**
 * Created by quliang on 16-8-4.
 */
public class JoinGroupResp extends BaseResp {

    private int    state;

    public JoinGroupResp(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
