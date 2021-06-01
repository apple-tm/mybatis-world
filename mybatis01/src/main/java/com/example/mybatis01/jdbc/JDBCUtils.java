package com.example.mybatis01.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    private static String  driver = null;
    private static String  url = null;
    private static String  username = null;
    private static String  password = null;

    static {
        try {
            //获取配置文件的读入流
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");

            Properties properties = new Properties();
            properties.load(inputStream);

            //获取配置文件的信息
            driver = properties.getProperty("mysql.driver");
            url = properties.getProperty("mysql.url");
            username = properties.getProperty("mysql.username");
            password = properties.getProperty("mysql.password");

            //加载驱动类
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int update(String sql, Object[] objects) {
        Connection connection = null;
        PreparedStatement ps = null;
        int count=0;

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            for (int i=0; i<objects.length; i++) {
                ps.setObject(i+1, objects[i]);
            }

            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, ps, null);
        }

        return count;
    }

    public static Object query(String sql, Object[] objs, ResultSetHandler rsh) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            for (int i=0; i<objs.length; i++) {
                ps.setObject(i+1, objs[i]);
            }

            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rsh.handler(rs);
    }
}

