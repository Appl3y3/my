package com.appleye.easyquery.entity;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 20:18
 **/
public class StringColumn extends Column{

    public StringColumn(String data) {
        super(data, Column.Type.STRING);
    }
}
