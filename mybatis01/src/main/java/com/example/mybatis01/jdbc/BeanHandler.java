package com.example.mybatis01.jdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BeanHandler<T> implements ResultSetHandler {

    private Class clazz;

    public BeanHandler(Class clazz) {
        this.clazz = clazz;
    }

    public T handler(ResultSet resultSet) {
        try {
            Object obj = clazz.newInstance();
            if (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int i=0; i<metaData.getColumnCount(); i++) {
                    // 获取每列的列名
                    String colName = metaData.getColumnName(i+1);
                    String colData = resultSet.getString(i+1);

                    Field declaredField = clazz.getDeclaredField(colName);
                    declaredField.setAccessible(true);
                    declaredField.set(obj,colData);
                }
            }
            return (T)obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
