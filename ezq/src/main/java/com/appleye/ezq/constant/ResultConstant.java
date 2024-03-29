package com.appleye.ezq.constant;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-23 23:51
 **/
public enum ResultConstant {
    SUCCESS(200, "成功"),
    FAILURE(201, "失败");

    ResultConstant(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
