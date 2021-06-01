package com.example.mybatis01.jdbc;

import java.sql.*;

/**
 * SQL 注入
 * 1.PreparedStatement 避免 SQL 注入的主要功能是对参数进行了转义
 * 2.' 被转义为了 \'
 * 3.Statement不转义，"1' or '1'='1" 参数被拼接为 '1' or '1'='1'
 */
public class SQLInject {

    public static void testSQLInject(String userName, String password) {
        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            String sql1 = "select * from mybatis01_user where user_name= "+ "'" + userName+"'" + " and pwd= "+ "'" +password+"'";
            ResultSet resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                System.out.print(resultSet.getObject(1));
                System.out.print(resultSet.getObject(2));
                System.out.print(resultSet.getObject(3));
                System.out.println("成功");
            }
            JDBCUtils.release(connection, statement, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testSQLInject2(String userName, String password) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql2 = "SELECT * FROM mybatis01_user WHERE user_name = ? and pwd= ? ";
            PreparedStatement ps = connection.prepareStatement(sql2);
            ps.setString(1,userName);
            ps.setString(2,password);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                System.out.print(resultSet.getObject(1));
                System.out.print(resultSet.getObject(2));
                System.out.print(resultSet.getObject(3));
                System.out.println("成功");
            }
            JDBCUtils.release(connection, ps, resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testGetPrimaryKey(String userName, String password) {
        try {
            Connection connection = JDBCUtils.getConnection();

            String sql2 = "insert into mybatis01_user(user_name, pwd) value (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,userName);
            ps.setString(2,password);

            ResultSet resultSet;
            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }

            JDBCUtils.release(connection, ps, resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testTransaction() {
        Connection connection=null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);

            //A账户减去500块
            String sql = "UPDATE a SET money=money-500 ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            // 模拟异常
            int a = 3 / 0;

            String sql2 = "UPDATE b SET money=money+500";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();

            //如果程序能执行到这里，没有抛出异常，我们就提交数据
            connection.commit();
            //关闭事务【自动提交】
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            //如果出现了异常，就会进到这里来，我们就把事务回滚【将数据变成原来那样】
            try {
                connection.rollback();
                //关闭事务【自动提交】
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void testUpdateAndQuery() {
        String updateSql = "insert into mybatis01_user(user_name, pwd) values(?, ?)";
        Object[] objs = new Object[2];
        objs[0] = new String("张三");
        objs[1] = new String("1234");

        int count = JDBCUtils.update(updateSql, objs);

        if (count > 0) {
            String querySql  = "select * from mybatis01_user where user_name = ? and pwd = ?";
            Object[] objs2 = new Object[2];
            objs2[0] = new String("张三");
            objs2[1] = new String("1234");
            Mybatis01User result = (Mybatis01User)JDBCUtils.query(querySql, objs2, new BeanHandler(Mybatis01User.class));
            System.out.println(result);
        }

    }

    public static void main(String[] args) {
//        testSQLInject("root", "root");
        // select * from mybatis01_user where user_name= 'wl' and pwd= '1' or '1'='1'
//        testSQLInject("", "1' or '1'='1");

        // SELECT * FROM mybatis01_user WHERE user_name = 'wl' and pwd= '1\' or \'1\'=\'1'
//        testSQLInject2("", "1' or '1'='1");
//        testSQLInject2("", "1' or '1'='1");


//        testSQLInject2("wl", "wl");

//        testGetPrimaryKey("zhansan", "li");
        testUpdateAndQuery();
    }
}
