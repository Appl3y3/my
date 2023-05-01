package com.appleye.ezq.entity;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-23 23:46
 **/
public class ExecutorParameter {

    private String sql;
    private int pageNum;
    private int pageSize;
    private int total;
    private DataSource dataSource;

    public String getSql() {
        return sql;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
