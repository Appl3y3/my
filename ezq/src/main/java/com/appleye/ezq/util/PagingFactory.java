package com.appleye.ezq.util;

import com.appleye.ezq.constant.DBType;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-24 00:08
 **/
public class PagingFactory {
    private static final CommonPaging COMMON_PAGING = new CommonPaging();
    private static Map<DBType, Paging> map;
    static {
        map = new HashMap<>();
        map.put(DBType.MYSQL, COMMON_PAGING);
    }

    public static Paging getPaging(DBType dbType){
        return map.get(dbType);
    }
}
