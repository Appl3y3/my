package com.appleye.ezq.service.serviceImpl;

import com.appleye.ezq.constant.DBType;
import com.appleye.ezq.entity.*;
import com.appleye.ezq.service.EasyQueryService;
import com.appleye.ezq.util.Paging;
import com.appleye.ezq.util.PagingFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-04-23 23:23
 **/
@Service
public class EasyQueryServiceImpl implements EasyQueryService {
    @Override
    public Grid getExecuteResult(ExecutorParameter ep) {
        //sql处理:①去除空格、②行尾分号
        String trim = ep.getSql().trim();
        String pureSql = trim.endsWith(";") ? trim.substring(0, trim.length() - 1) : trim;

        try {
            Connection conn = DriverManager.getConnection(ep.getDataSource().getUrl(), ep.getDataSource().getUsername(), ep.getDataSource().getPassword());

            //sql分页，sql结果总行数
            Paging paging = PagingFactory.getPaging(DBType.valueOf(conn.getMetaData().getDatabaseProductName().toUpperCase()));
            String pagedSql = paging.getPagedSql(pureSql, ep.getPageNum(), ep.getPageSize());
            String countSql = paging.getCountSql(pureSql);

            PreparedStatement countPs = conn.prepareStatement(countSql);
            countPs.execute();
            ResultSet countRs = countPs.getResultSet();
            int recordCount = 0;
            while (countRs.next()) {
                recordCount = countRs.getInt(1);
            }
            

            PreparedStatement ps = conn.prepareStatement(pagedSql);
            boolean execute = ps.execute();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();

            Header header = new Header(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                header.getRow().add(new Cell(md.getColumnName(i), Types.VARCHAR));
            }

            List<RecordRow> recordRows = new ArrayList<>();
            while (rs.next()){
                RecordRow recordRow = new RecordRow(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    switch (rs.getMetaData().getColumnType(i)) {
                        case Types.INTEGER:
                        case Types.TINYINT:
                        case Types.SMALLINT:
                            recordRow.getRow().add(new Cell(rs.getInt(i), rs.getMetaData().getColumnType(i)));
                            break;
                        case Types.DOUBLE:
                        case Types.FLOAT:
                            recordRow.getRow().add(new Cell(rs.getDouble(i), rs.getMetaData().getColumnType(i)));
                            break;
                        case Types.BIGINT:
                        case Types.NUMERIC:
                        case Types.VARCHAR:
                        case Types.CLOB:
                        case Types.CHAR:
                            recordRow.getRow().add(new Cell(rs.getString(i), rs.getMetaData().getColumnType(i)));
                            break;
                        case Types.DATE:
                        case Types.TIME:
                        case Types.TIMESTAMP:
                            recordRow.getRow().add(new Cell(rs.getDate(i), rs.getMetaData().getColumnType(i)));
                            break;
                        default:
                            recordRow.getRow().add(new Cell());
                            break;
                    }
                }
                recordRows.add(recordRow);
            }

            return new Grid(header, recordRows);
            //执行
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
