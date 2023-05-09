package com.appleye.easyquery.controller;

import com.appleye.easyquery.constant.ResultConstant;
import com.appleye.easyquery.entity.ExecutorParameter;
import com.appleye.easyquery.entity.Grid;
import com.appleye.easyquery.entity.Result;
import com.appleye.easyquery.service.EasyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Description: TODO
 *
 * @Author: Appleye
 * @CreateTime: 2023-04-30 13:52
 **/
@RestController("/easyquery")
@CrossOrigin
public class EasyQueryController {

    @Autowired
    EasyQueryService easyQueryService;

    @PostMapping("/getResult")
    public Result getExecuteResult(@RequestBody ExecutorParameter paras) {
        Grid grid = easyQueryService.getGrid(paras);
        return new Result(ResultConstant.SUCCESS, grid);
    }


}
