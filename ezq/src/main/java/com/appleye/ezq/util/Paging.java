package com.appleye.ezq.util;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-23 23:59
 **/
public interface Paging {
    String getPagedSql(String sql, Integer pageNum, Integer pageSize);

    String getCountSql(String sql);
}
