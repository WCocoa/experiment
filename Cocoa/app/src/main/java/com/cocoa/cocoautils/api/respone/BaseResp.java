package com.cocoa.cocoautils.api.respone;

/**
 * ClassName:
 * author: Cocoa
 * date: 2016/10/18.
 */

public class BaseResp<T> {
    private int    code;
    private String message;
    private T      success;

    public BaseResp() {
    }

    public BaseResp(T success, String message, int code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getSuccess() {
        return success;
    }

    public void setSuccess(T success) {
        this.success = success;
    }
}
