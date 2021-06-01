package com.example.mybatis01.jdbc;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {

    T handler(ResultSet resultSet);
}
