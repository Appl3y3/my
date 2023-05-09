package com.appleye.easyquery.service;

import com.appleye.easyquery.entity.ExecutorParameter;
import com.appleye.easyquery.entity.Grid;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 14:09
 **/
public interface EasyQueryService {
    /**
     * 返回查询结果
     * @param paras 查询参数
     * @return Grid 结果
     */
    Grid getGrid(ExecutorParameter paras);
}
