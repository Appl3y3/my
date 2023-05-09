package com.appleye.easyquery.entity;

import java.math.BigDecimal;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 20:36
 **/
public class DoubleColumn extends Column{
    public DoubleColumn(String data) {
        super(null, Column.Type.STRING);
        if (null == data) { return; }
        setRawData(new BigDecimal(data));
    }
}
