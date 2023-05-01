package com.appleye.ezq.entity;

import java.util.ArrayList;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-30 13:58
 **/
public class Header extends AbstractRow {
    public Header(int capacity) {
        setRow(new ArrayList<Cell>(capacity));
    }

}
