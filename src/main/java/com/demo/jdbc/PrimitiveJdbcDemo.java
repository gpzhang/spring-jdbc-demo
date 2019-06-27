package com.demo.jdbc;

import java.sql.*;

/**
 * 原始jdbc 使用demo
 *
 * @author haishen
 * @date 2019/3/30
 */
public class PrimitiveJdbcDemo {

    private static final String URL = "jdbc:mysql://localhost:3306/my_database";
    private static final String USER = "hs_conn";
    private static final String PASSWORD = "hs_conn";

    public static void jdbc() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //反向加载驱动程序
            //获得数据库的连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false);
            //获取Statement对象
            Statement stmt = conn.createStatement();

            //执行sql语句，获取结果集
            ResultSet rs = stmt.executeQuery("SELECT id,name,age FROM user");

            conn.commit();

            //获取返回的数据
            while (rs.next()) {
                System.out.println("用户名：" + rs.getString("name") + "  年龄：" + rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


