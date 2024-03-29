package com.appleye.ezq.entity;

import com.appleye.ezq.constant.ResultConstant;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-23 23:49
 **/
public class Result<T> {
    Integer code;
    String message;
    T data;

    public Result(ResultConstant resultConstant, T data) {
        this.code = resultConstant.getCode();
        this.message = resultConstant.getMessage();
        this.data = data;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
