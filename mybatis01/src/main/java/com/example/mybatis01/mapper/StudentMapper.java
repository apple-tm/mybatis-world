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

    public List<Student> findAll() throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            return sqlSession.selectList("StudentID.findAll");
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtil.closeSqlSession();
        }
    }

    public void delete(int id) throws Exception{
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            sqlSession.delete("StudentID.delete",id);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtil.closeSqlSession();
        }
    }

    public void updata(Student student) throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            sqlSession.update("StudentID.update", student);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }

    }

    //分页
    public List<Student> pagination(int start,int end)throws Exception{
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            Map<String, Object> map = new HashMap();
            map.put("start", start);
            map.put("end", end);
            return sqlSession.selectList("StudentID.pagination", map);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtil.closeSqlSession();
        }
    }

//    动态SQL
public List<Student> findByCondition(String name,Double sal) throws Exception {
    //得到连接对象
    SqlSession sqlSession = MybatisUtil.getSqlSession();
    try{
        //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
        /**
         * 由于我们的参数超过了两个，而方法中只有一个Object参数收集
         * 因此我们可以使用Map集合来装载我们的参数
         */
        Map<String, Object> map = new HashMap();
        map.put("name", name);
        map.put("sal", sal);
        return sqlSession.selectList("StudentID.findByCondition", map);
    }catch(Exception e){
        e.printStackTrace();
        sqlSession.rollback();
        throw e;
    }finally{
        MybatisUtil.closeSqlSession();
    }
}

    public void updateByConditions(int id,String name,Double sal) throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            Map<String, Object> map = new HashMap();
            map.put("id", id);
            map.put("name", name);
            map.put("sal", sal);
            sqlSession.update("StudentID.updateByConditions", map);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }

    public void deleteByConditions(int... ids) throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            sqlSession.delete("StudentID.deleteByConditions", ids);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }

    public void insertByConditions(Student student) throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            sqlSession.insert("StudentID.insertByConditions", student);
            sqlSession.commit();
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

//        Student student = new Student();
//        student.setId(3);
//        student.setName("wuhai123");
//        student.setSal(10000.013D);
//        studentDao.add(student);
//        Student student1 = studentDao.findById(3);
//        System.out.println(student1);

//        List<Student> students = studentDao.findAll();
//        System.out.println(students.size());

//        studentDao.delete(2);

//        Student student = new Student();
//        student.setId(1);
//        student.setName("wuhai");
//        student.setSal(666.66D);
//        studentDao.updata(student);

//        List<Student> students = studentDao.pagination(0,5);
//        for (Student student:students) {
//            System.out.println(student.getId());
//        }

//        List<Student> students = studentDao.findByCondition(null,90000D);
//        for (Student student:students){
//            System.out.println(student.getId() + "---" + student.getName()+ "---" + student.getSal());;
//
//        }

        studentDao.updateByConditions(2,"hg",5900D);

//        studentDao.deleteByConditions(2,3,4);

//        studentDao.insertByConditions(new Student(3, "gsd", 2421D));
//
//        studentDao.insertByConditions(new Student(4, "fds", 424D));
//        studentDao.insertByConditions(new Student(5, "fd", 3999d));



    }
}
