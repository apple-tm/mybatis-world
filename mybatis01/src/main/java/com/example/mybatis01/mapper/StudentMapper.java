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
            int primarykey = sqlSession.insert("StudentID.add", student);
            //mybatis 默认开启事务 因此我们在完成操作以后，需要我们手动去提交事务！
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }

        public Student findById(String name, String pwd) throws Exception {
        //得到连接对象
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", name);
            map.put("pwd", pwd);
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            return sqlSession.selectOne("StudentID.findById",map);
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
        Student student = new Student("wu", 190.1);
        studentMapper.add(student);
        // inject 1 or 1=1
//        Student student2 = studentMapper.findById("wuhai", "1 or 1=1");
//        Student student21 = studentMapper.findById("wuhai", "1 or 1=1");
//
//        if (student2 !=null) {
//            System.out.println("登录成功");
//        } else {
//            System.out.println("登录失败");
//        }
//        Student student3 = studentMapper.findById("'' or 1=1");
//        Student student4 = studentMapper.findById("'' or 1=1");

//        System.out.println(student2);
    }
}
