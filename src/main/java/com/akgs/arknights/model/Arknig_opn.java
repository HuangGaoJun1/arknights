package com.akgs.arknights.model;

import java.util.ArrayList;
import java.util.List;

public class Arknig_opn {
    private  Integer id;  //职业ID
    private  String name; //职业名称

    public Arknig_opn() {
    }

    public Arknig_opn(int id) {
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
