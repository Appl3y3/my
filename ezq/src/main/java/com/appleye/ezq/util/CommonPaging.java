package com.appleye.ezq.util;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-24 00:00
 **/
public class CommonPaging implements Paging{
    @Override
    public String getPagedSql(String sql, Integer pageNum, Integer pageSize) {
        String templates = "select * from (%s) a limit %d, %d";
        return String.format(templates, sql, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public String getCountSql(String sql) {
        return String.format("select count(*) from (%s) a", sql);
    }

}
