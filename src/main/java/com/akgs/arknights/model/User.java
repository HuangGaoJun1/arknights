package com.akgs.arknights.model;

public class User {
    private String username;
    private String password;
    private Integer id;
    private  String name;
    private  String imges;
    private  String commrnt;
    private  String phone;

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

    public String getCommrnt() {
        return commrnt;
    }

    public void setCommrnt(String commrnt) {
        this.commrnt = commrnt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImges() {
        return imges;
    }

    public void setImges(String imges) {
        this.imges = imges;
    }
}
