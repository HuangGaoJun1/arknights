package com.akgs.arknights.model;


public class Arknig_enemy {
    private  Integer id;    //敌方ID
    private  String imges;  //敌方图片
    private  String name;   //敌人名称
    private  String number;  //敌方编号
    private  String category; //敌方类别
    private  String attackmode; //敌方攻击方式
    private  String durable;    //敌方耐久
    private  String aggressivity; //敌方攻击力
    private  String defensive;    //敌方防御力
    private  String weight;      //重量
    private  String resistance;   //敌方法术抗性
    private  String grouping;     //敌方分组
    private  String introduce;    //敌方介绍
    private  String remarks;     //敌方备注


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImges() {
        return imges;
    }

    public void setImges(String imges) {
        this.imges = imges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAttackmode() {
        return attackmode;
    }

    public void setAttackmode(String attackmode) {
        this.attackmode = attackmode;
    }

    public String getDurable() {
        return durable;
    }

    public void setDurable(String durable) {
        this.durable = durable;
    }

    public String getAggressivity() {
        return aggressivity;
    }

    public void setAggressivity(String aggressivity) {
        this.aggressivity = aggressivity;
    }

    public String getDefensive() {
        return defensive;
    }

    public void setDefensive(String defensive) {
        this.defensive = defensive;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
