package com.appleye.ezq.controller;

import com.appleye.ezq.constant.ResultConstant;
import com.appleye.ezq.entity.ExecutorParameter;
import com.appleye.ezq.entity.Grid;
import com.appleye.ezq.entity.Result;
import com.appleye.ezq.service.EzqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 *
 * @Author: Appleye
 * @CreateTime: 2023-04-30 13:52
 **/
@RestController("/ezq")
@CrossOrigin
public class EasyQueryController {
    @Autowired
    private EzqService easyQueryService;


    @PostMapping("/getExecuteResult")
    public Result<List<Map<String, Object>>> getExecuteResult(@RequestBody ExecutorParameter executorParameter) {
        Grid grid = easyQueryService.getExecuteResult(executorParameter);
        List mapList = grid.getMapList();
        return new Result(ResultConstant.SUCCESS, mapList);
    }

    @Deprecated
    @PostMapping("/getGridResult")
    public Result<List<Map<String, Object>>> getGridResult(@RequestBody ExecutorParameter executorParameter) {
        Grid grid = easyQueryService.getExecuteResult(executorParameter);
        return new Result(ResultConstant.SUCCESS, grid);
    }

}
