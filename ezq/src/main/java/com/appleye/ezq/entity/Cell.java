package com.appleye.ezq.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-30 14:01
 **/
public class Cell {
    private Object value;
    private int type;

    public Cell() {}

    public Cell(Object value, int type) {
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type) {
            case Types.VARCHAR:
                return value.toString();
            case Types.INTEGER:
                return Integer.toString((Integer) value);
            case Types.TIMESTAMP:
                return ((Date) value).toString();
        }
        return null;
    }
}
