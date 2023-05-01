package com.appleye.ezq.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-30 14:17
 **/
public abstract class AbstractRow {
    private ArrayList<Cell> row;

    public List<Cell> getRow() {
        return row;
    }
    public void setRow(ArrayList<Cell> row) {
        this.row = row;
    }

    public Integer getCellCount(){
        return row.size();
    }

}
