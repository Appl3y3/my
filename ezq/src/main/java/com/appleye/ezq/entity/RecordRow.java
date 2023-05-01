package com.appleye.ezq.entity;

import java.util.ArrayList;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-30 14:03
 **/
public class RecordRow extends AbstractRow {

    public RecordRow(int capacity) {
        setRow(new ArrayList<Cell>(capacity));
    }

    public String getStringByDelimiter(String delimiter) {
        StringBuilder sb = new StringBuilder();

        sb.append(super.getRow().get(0));
        for (int i = 1; i < super.getRow().size(); i++) {
            sb.append(delimiter).append(super.getRow().get(i));
        }

        return sb.toString();
    }
}
