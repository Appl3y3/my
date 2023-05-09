package com.appleye.ezq.service;

import com.appleye.ezq.entity.ExecutorParameter;
import com.appleye.ezq.entity.Grid;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-23 23:22
 **/
public interface EzqService {
    Grid getExecuteResult(ExecutorParameter executorParameter);
}
