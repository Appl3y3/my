package com.appleye.easyquery.utils;

import com.appleye.easyquery.entity.DataSource;
import com.appleye.easyquery.entity.ExecutorParameter;

import java.sql.*;

/**
 * @Description: TODO
 * @Author: Appleye
 * @CreateTime: 2023-05-07 15:15
 **/
public class DBUtil {
    public static Connection getConnection(DataSource dataSource) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static PreparedStatement getPreparedStatement(Connection conn, String sql) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getResultSet(PreparedStatement ps, Integer maxRows) {
        try {
            if (null != maxRows ) { ps.setMaxRows(maxRows); }
            ps.execute();
            return ps.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (null != rs) { rs.close();}
            if (null != ps) { ps.close();}
            if (null != conn) { conn.close();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getResultSet(ExecutorParameter paras, int maxRows) {
        Connection conn = getConnection(paras.getDataSource());
        PreparedStatement ps = getPreparedStatement(conn, paras.getSql());
        ResultSet rs = getResultSet(ps, maxRows);
        return rs;
    }
}
