package com.appleye.easyquery.service.impl;

import com.appleye.easyquery.entity.Column;
import com.appleye.easyquery.entity.ExecutorParameter;
import com.appleye.easyquery.entity.Grid;
import com.appleye.easyquery.service.EasyQueryService;
import com.appleye.easyquery.utils.DBUtil;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 14:10
 **/
@Service
public class EasyQueryServiceImpl implements EasyQueryService {
    @Override
    public Grid getGrid(ExecutorParameter paras) {
        int maxRows = 1_000;
        ResultSet rs = DBUtil.getResultSet(paras, maxRows);
        return new Grid(rs);
    }
}
