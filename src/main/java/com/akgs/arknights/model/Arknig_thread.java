package com.akgs.arknights.model;

public class Arknig_thread {
    private  Integer id;           //ID
    private  String name;          //任务名称
    private  String thread_id;     //主线任务ID
    private  Integer chapter;       //任务章节
    private  boolean difficulty;   //任务难度   判断任务难度   1=突袭  0=普通
    private  String imges;         //任务图片
    private  String consume;       //消耗
    private  String deploy;        //部署上限
    private  String grade;         //推荐等级
    private  String firstfall;     //首次掉落
    private  String drop;          //常规掉落
    private  String material;      //额外掉落物资
    private  String intelligence;  //敌方情报
    private  String introduce;     //介绍
    private  String remarks;       //备注
    private  String unlock;        //解锁条件
    private  String special;       //特殊条件

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

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public Integer getChapter() {
        return chapter;
    }

    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }

    public boolean getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(boolean difficulty) {
        this.difficulty = difficulty;
    }

    public String getImges() {
        return imges;
    }

    public void setImges(String imges) {
        this.imges = imges;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getDeploy() {
        return deploy;
    }

    public void setDeploy(String deploy) {
        this.deploy = deploy;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFirstfall() {
        return firstfall;
    }

    public void setFirstfall(String firstfall) {
        this.firstfall = firstfall;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
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

    public String getUnlock() {
        return unlock;
    }

    public void setUnlock(String unlock) {
        this.unlock = unlock;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}
