package com.appleye.easyquery.entity;

import org.springframework.util.NumberUtils;

import java.math.BigInteger;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 20:22
 **/
public class LongColumn extends Column{
    public LongColumn(String data) {
        super(null, Type.LONG);
        if (null == data) { return; }
        setRawData(new BigInteger(data));
    }
}
