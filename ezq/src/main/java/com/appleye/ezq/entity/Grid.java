package com.appleye.ezq.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-30 14:25
 **/
public class Grid {
    // 表头
    private Header header;

    //数据行
    private List<RecordRow> recordRows;

    public Grid(Header header, List<RecordRow> recordRows) {
        this.header = header;
        this.recordRows = recordRows;
    }

    /**
     * 获取总行数
     * @return
     */
    public Integer getRecordCount(){
        return recordRows.size();
    }

    /**
     * 获取字段个数
     * @return
     */
    public Integer getHeaderCount(){
        return header.getCellCount();
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<RecordRow> getRecordRows() {
        return recordRows;
    }

    public void setRecordRows(List<RecordRow> recordRows) {
        this.recordRows = recordRows;
    }


    /**
     * 转换为vue可映射对象
     * @return
     */
    public List<Map<String, Object>> getMapList(){
        Integer recordCount = getRecordCount();
        Integer headerCount = getHeaderCount();

        List mapList = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map;
        for (int i = 0; i < recordCount; i++) {
            map = new HashMap<>();
            for (int j = 0; j < headerCount; j++) {
                map.put(header.getRow().get(j).toString(), recordRows.get(i).getRow().get(j).getValue());
            }
            mapList.add(map.clone());
        }
        return mapList;
    }

}
