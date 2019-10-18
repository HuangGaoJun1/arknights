package com.akgs.arknights.model;

public class Arknig_skill {
    private Integer role_id;              //技能ID
    private Integer id;               //角色ID
    private String skill_name;            //技能名
    private String skill_label;           //技能标签
    private String skill_grade;           //技能等级
    private String skill_describe;        //技能描述
    private Integer skill_consume;         //技能消耗
    private Integer skill_initial;         //技能初始
    private String skill_continued;       //技能持续时间
    private String skill_grade_imges;     //角色等级图片

    public Integer getRole_id() { return role_id; }

    public void setRole_id(Integer role_id) { this.role_id = role_id; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getSkill_label() {
        return skill_label;
    }

    public void setSkill_label(String skill_label) {
        this.skill_label = skill_label;
    }

    public String getSkill_grade() {
        return skill_grade;
    }

    public void setSkill_grade(String skill_grade) {
        this.skill_grade = skill_grade;
    }

    public String getSkill_describe() {
        return skill_describe;
    }

    public void setSkill_describe(String skill_describe) {
        this.skill_describe = skill_describe;
    }

    public Integer getSkill_consume() {
        return skill_consume;
    }

    public void setSkill_consume(Integer skill_consume) {
        this.skill_consume = skill_consume;
    }

    public Integer getSkill_initial() {
        return skill_initial;
    }

    public void setSkill_initial(Integer skill_initial) {
        this.skill_initial = skill_initial;
    }

    public String getSkill_continued() {
        return skill_continued;
    }

    public void setSkill_continued(String skill_continued) {
        this.skill_continued = skill_continued;
    }

    public String getSkill_grade_imges() {
        return skill_grade_imges;
    }

    public void setSkill_grade_imges(String skill_grade_imges) {
        this.skill_grade_imges = skill_grade_imges;
    }
}
