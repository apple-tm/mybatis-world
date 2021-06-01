package com.example.mybatis01.jdbc;

public class Mybatis01User {

    private String id;

    private String user_name;

    private String pwd;

    public Mybatis01User() {
    }

    public Mybatis01User(String userName, String pwd) {
        this.user_name = userName;
        this.pwd = pwd;
    }

    public Mybatis01User(String id, String userName, String pwd) {
        this.id = id;
        this.user_name = userName;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Mybatis01User{" +
                "id=" + id +
                ", userName='" + user_name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
