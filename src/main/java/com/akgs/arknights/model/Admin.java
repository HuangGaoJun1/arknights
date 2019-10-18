package com.akgs.arknights.model;


public class Admin {

    private String username; //管理员登录账户名
    private String password; //管理员登录密码
    private Integer id;      //管理员ID
    private  String name;    //管理员名称

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
