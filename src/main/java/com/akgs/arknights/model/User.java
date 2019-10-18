package com.akgs.arknights.model;

public class User {
    private String username;   //用户登录账户名
    private String password;   //用户登录密码
    private Integer id;        //用户ID
    private  String name;      //用户名称
    private  String phone;     //用户手机号码
    private  String mail;      //用户邮箱

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
