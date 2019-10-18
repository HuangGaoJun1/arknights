package com.akgs.arknights.model;

public class Arknig_label {
    private  Integer id;  //标签ID
    private  String name;  //标签名称

    public Arknig_label() {
    }

    public Arknig_label(int id) {
        this.id=id;
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
