package com.example.mybatis01.mapper;

import com.example.mybatis01.entity.Student;
import com.example.mybatis01.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class StudentMapper {


    public void add(Student student) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            sqlSession.insert("StudentID.add", student);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }

    public Student findById(int id) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            return sqlSession.selectOne("StudentID.findById",id);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }

    public static void main(String[] args) throws Exception {

        StudentMapper studentDao = new StudentMapper();

        Student student = new Student();
        student.setId(3);
        student.setName("wuhai123");
        student.setSal(10000.013D);
        studentDao.add(student);
        Student student1 = studentDao.findById(3);
        System.out.println(student1);
    }
}
