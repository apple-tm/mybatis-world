package com.example.mybatis01.mapper;

/**
 * 学习 MySQL 预编译、缓存、批处理
 *
 * 1.默认使用PreparedStatement是不能执行预编译的
 * 2.需要在url中给出useServerPrepStmts=true参数，Connector/J在5.0.5以后的版本，默认是没有开启预编译功能的
 * 3.jdbc 协议 url 加上参数?useServerPrepStmts=true 开启预编译
 * 4.预编译作用：保证mysql驱动会先把SQL语句发送给服务器进行预编译，然后在执行executeQuery()时只是把参数发送给服务器
 * 5.使用不同的PreparedStatement对象来执行相同的SQL语句时，还是会出现编译两次的现象
 * 6.针对第五点，驱动没有缓存编译后的函数key，导致二次编译。如果希望缓存编译后函数的key.
 * 7.针对第六点，设置jdbc 协议加上 url 参数cachePrepStmts=true
 * 8.批处理:MySQL默认是关闭批处理的，所以我们在默认状态下（批处理未打开）向数据库中存入10000条数据耗费时间412764MS
 * 9.针对第九点，?rewriteBatchedStatements=true，耗时301MS，速度快了1000倍以上
 */
public class PreparedStatementTest {

    /**
     * 预编译三步骤：
     * MySQL执行预编译分为如三步：
     * 执行预编译语句，例如：prepare myfun from 'select * from t_book where bid=?'
     * 设置变量，例如：set @str='b1'
     * 执行语句，例如：execute myfun using @str
     *
     * 如果需要再次执行myfun，那么就不再需要第一步，即不需要再编译语句了：
     * 设置变量，例如：set @str='b2'
     * 执行语句，例如：execute myfun using @str
     */


}
