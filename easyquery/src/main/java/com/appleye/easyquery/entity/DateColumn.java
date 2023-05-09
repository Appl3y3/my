package com.appleye.easyquery.entity;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 20:45
 **/
public class DateColumn extends Column{

    private DateType subType = DateType.DATETIME;

    public enum DateType {
        DATE, TIME, DATETIME
    }

    public DateColumn(Date date) {
        this(date == null ? null : date.getTime());
        setSubType(DateType.DATE);
    }

    public DateColumn(Time time) {
        this(time == null ? null : time.getTime());
        this.setSubType(DateType.TIME);
    }

    public DateColumn(Timestamp timestamp) {
        this(timestamp == null ? null : timestamp.getTime());
        this.setSubType(DateType.DATETIME);
    }

    public DateColumn(Long stamp) {
        super(stamp, Column.Type.DATE);
    }

    public DateType getSubType() {
        return subType;
    }

    public void setSubType(DateType subType) {
        this.subType = subType;
    }

    @Override
    public Object getRawData() {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond((Long) super.getRawData() / 1000, 0, ZoneOffset.UTC);
        return dateTime;
    }

    @Override
    public String toString() {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond((Long) super.getRawData() / 1000, 0, ZoneOffset.UTC);
        return dateTime.toString();
    }
}
