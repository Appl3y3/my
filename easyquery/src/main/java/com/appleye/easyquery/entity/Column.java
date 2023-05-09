package com.appleye.easyquery.entity;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-02 12:28
 **/
public abstract class Column {

    private Type type;

    private Object rawData;

    public Column(final Object object, final Type type) {
        this.rawData = object;
        this.type = type;
    }

    public Object getRawData() {
        return this.rawData;
    }

    private Type getType() {
        return this.type;
    }

    protected void setType(Type type) {
        this.type = type;
    }

    protected void setRawData(Object rawData) {
        this.rawData = rawData;
    }

/*    public abstract Long asLong();

    public abstract Double asDouble();

    public abstract String asString();

    public abstract Date asDate();

    public abstract Date asDate(String dateFormat);

    public abstract byte[] asBytes();

    public abstract Boolean asBoolean();

    public abstract BigDecimal asBigDecimal();

    public abstract BigInteger asBigInteger();*/

    @Override
    public String toString() {
        return rawData.toString();
    }

    public enum Type {
        BAD, NULL, INT, LONG, DOUBLE, STRING, BOOL, DATE, BYTES
    }
}
