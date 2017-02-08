package com.qiantang.neighbourmother.business.data;

/**
 * @author quliang
 * @version 2015-8-22 下午4:58:32
 *          desc
 */
public class BaseDataResp {
    private int code;
    private String message;
    private String success;

    public BaseDataResp() {
    }

    public BaseDataResp(String success, String message, int code) {
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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
