package com.appleye.easyquery.constant;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 14:48
 **/
public enum ResultConstant {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败");

    private int code;
    private String message;

    private ResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
