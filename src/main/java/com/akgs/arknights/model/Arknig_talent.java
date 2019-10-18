package com.akgs.arknights.model;

public class Arknig_talent {
    private  Integer role_id; //天赋ID
    private  Integer id;   //角色ID
    private  String talent_name;  //天赋名
    private  String describe;     //描述
    private  String unlocking;    //精英化

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTalent_name() {
        return talent_name;
    }

    public void setTalent_name(String talent_name) {
        this.talent_name = talent_name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUnlocking() {
        return unlocking;
    }

    public void setUnlocking(String unlocking) {
        this.unlocking = unlocking;
    }
}
