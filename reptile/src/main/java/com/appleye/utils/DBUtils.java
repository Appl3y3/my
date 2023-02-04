package com.appleye.utils;

import java.sql.*;

public class DBUtils {

    public static Connection getConn(String driver, String url, String username, String password){

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }


    public static void insert(Connection conn, String sql) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute(sql);
    }

    public static ResultSet select(Connection conn, String sql) {

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
