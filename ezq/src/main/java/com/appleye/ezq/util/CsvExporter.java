package com.appleye.ezq.util;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CsvExporter {

    public static void main(String[] args) {
        String tableName = "table_name"; // 要导出的表名
        String csvFilePath = "output.csv"; // 导出的 CSV 文件路径

        // 数据库连接信息
        String dbUrl = "jdbc:mysql://localhost:3306/database_name";
        String username = "root";
        String password = "password";

        try {
            // 加载 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 连接数据库
            Connection conn = DriverManager.getConnection(dbUrl, username, password);

            // 创建 Statement 对象
            Statement stmt = conn.createStatement();

            // 查询数据并写入 CSV 文件
            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = stmt.executeQuery(sql);
            FileWriter writer = new FileWriter(csvFilePath);
            while (rs.next()) {
                String row = rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "\n";
                writer.write(row);
            }
            writer.close();
            System.out.println("导出成功");

            // 关闭数据库连接
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
