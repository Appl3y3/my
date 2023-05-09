package com.appleye.easyquery.entity;

import com.appleye.easyquery.constant.ResultConstant;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 14:28
 **/
public class Result<T> {
    int code;
    String message;
    T data;

    public Result(ResultConstant resultConstant, T data) {
        this.code = resultConstant.getCode();
        this.message = resultConstant.getMessage();
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
