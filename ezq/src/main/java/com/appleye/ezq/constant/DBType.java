package com.appleye.ezq.constant;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-23 23:19
 **/
public enum DBType {
    MYSQL("com.mysql.cj.jdbc.Driver");

    DBType(String driverClassName){
        this.driverClassName = driverClassName;
    }
    private String driverClassName;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
