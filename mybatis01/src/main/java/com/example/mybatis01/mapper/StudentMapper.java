package com.example.mybatis01.mapper;

import com.example.mybatis01.entity.Student;
import com.example.mybatis01.util.MybatisUtil;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
//import sun.security.mscapi.KeyStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentMapper {


    public void add(Student student) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            sqlSession.insert("StudentID.add", student);
            //mybatis 默认开启事务
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }

    public Student findById(String name) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            return sqlSession.selectOne("StudentID.findById",name);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }

    public static void main(String[] args) throws Exception {
        StudentMapper studentMapper = new StudentMapper();
//        Student student = new Student(20, "ws", 190.1);
//        studentMapper.add(student);
        Student student2 = studentMapper.findById("'' or 1=1");
        Student student3 = studentMapper.findById("'' or 1=1");
        Student student4 = studentMapper.findById("'' or 1=1");

        System.out.println(student2);
    }
}
