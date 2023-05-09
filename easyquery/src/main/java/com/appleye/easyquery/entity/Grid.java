package com.appleye.easyquery.entity;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 13:34
 **/
public class Grid {
    private List<Map<String, Object>> records;
    private int recordsCount;
    private int headersCount;
    private List<String> headers;

    private Grid() {}
    public Grid(List<Map<String, Object>> records) {
        this.records = records;
        this.headers = asHeaders();
        this.headersCount = this.headers.size();
        this.recordsCount = records.size();
    }
    public Grid(ResultSet rs) {
        this(getRecords(rs));
    }
    private List<String> asHeaders() {
        if (records.size() == 0) { throw new RuntimeException("没有数据记录"); }
        return new ArrayList<>(records.get(0).keySet());
    }

    public List<Map<String, Object>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }

    public int getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }

    public int getHeadersCount() {
        return headersCount;
    }

    public void setHeadersCount(int headersCount) {
        this.headersCount = headersCount;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public static List<Map<String, Object>> getRecords(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int headersCount = metaData.getColumnCount();
            Map<String, Integer> recordMeta = new LinkedHashMap<>(headersCount);
            for (int i = 1; i <= headersCount; i++) {
                recordMeta.put(metaData.getColumnLabel(i), metaData.getColumnType(i));
            }

            List<Map<String, Object>> records = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> recordRow = new LinkedHashMap<>(headersCount);
                for (String key : recordMeta.keySet()) {
                    recordRow.put(key, getColumn(recordMeta.get(key), rs, key).toString());
                }
                records.add(recordRow);
            }
            return records;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static Column getColumn(int columnType, ResultSet rs, String columnLabel) throws SQLException{
        switch (columnType) {
            case Types.CHAR:
            case Types.NCHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
            case Types.NVARCHAR:
            case Types.LONGNVARCHAR:
// TODO 字符集           new String((rs.getBytes(i) == null ? EMPTY_CHAR_ARRAY : rs.getBytes(i)), mandatoryEncoding)
            case Types.CLOB:
            case Types.NCLOB:
                return new StringColumn(rs.getString(columnLabel));
            case Types.INTEGER:
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.BIGINT:
                return new LongColumn(rs.getString(columnLabel));
            case Types.DOUBLE:
            case Types.FLOAT:
            case Types.NUMERIC:
            case Types.DECIMAL:
                return new DoubleColumn(rs.getString(columnLabel));
            case Types.DATE:
                return new DateColumn(rs.getDate(columnLabel));
            case Types.TIME:
                return new DateColumn(rs.getTime(columnLabel));
            case Types.TIMESTAMP:
                return new DateColumn(rs.getTimestamp(columnLabel));
            default:
                throw new RuntimeException(String.format("不支持的数据类型：字段名:[%s]", columnLabel));
        }
    }
}
