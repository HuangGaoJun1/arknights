package com.akgs.arknights.model;


import org.hibernate.validator.constraints.Range;

import java.util.HashMap;


public class Arknig_role {
    private Integer role_id;     //角色ID
    private Integer opn_id;      //角色职业ID
    private String label_id;     //角色标签ID
    private Integer star;        //角色星级
    @Range(min = 0, max = 2, message = "性别数值必须在 {min} - {max} 之间")
    private Boolean gender;      //角色性别
    private String name;         //角色名称
    private String camp;         //角色阵营
    private String camp_imges;   //角色阵营图片
    private String painter;      //角色画师
    private String CV;           //角色CV
    private String charact;      //角色特性
    private String accessWay;    //角色获取途径
    private String experience;   //角色战斗经验
    private String birth;        //角色出身
    private String birthday;     //角色生日
    private String race;         //角色种族
    private String height;       //角色身高
    private String oreDisInfe;   //角色矿石病感染情况
    private String objCurrVitae; //角色客观履历
    private String function;     //角色职能
    private String mastery;      //角色专精
    private String brief;        //角色简介
    private String role_imges;   //角色人物图片
    private String background_imges;  //角色背景图片
    @Range(min = 0, max = 2, message = "位置数值必须在 {min} - {max} 之间")
    private  Integer position;    //角色位置
    private  String natura;       //角色干员资历
    private  Boolean elite;
    private String elite_imges;  //角色精英化图片

    public Boolean getElite() {
        return elite;
    }

    public void setElite(Boolean elite) {
        this.elite = elite;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getOpn_id() {
        return opn_id;
    }

    public void setOpn_id(Integer opn_id) {
        this.opn_id = opn_id;
    }

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public String getCamp_imges() {
        return camp_imges;
    }

    public void setCamp_imges(String camp_imges) {
        this.camp_imges = camp_imges;
    }

    public String getPainter() {
        return painter;
    }

    public void setPainter(String painter) {
        this.painter = painter;
    }

    public String getCV() {
        return CV;
    }

    public void setCV(String CV) {
        this.CV = CV;
    }

    public String getCharact() {
        return charact;
    }

    public void setCharact(String charact) {
        this.charact = charact;
    }

    public String getAccessWay() {
        return accessWay;
    }

    public void setAccessWay(String accessWay) {
        this.accessWay = accessWay;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getOreDisInfe() {
        return oreDisInfe;
    }

    public void setOreDisInfe(String oreDisInfe) {
        this.oreDisInfe = oreDisInfe;
    }

    public String getObjCurrVitae() {
        return objCurrVitae;
    }

    public void setObjCurrVitae(String objCurrVitae) {
        this.objCurrVitae = objCurrVitae;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getMastery() {
        return mastery;
    }

    public void setMastery(String mastery) {
        this.mastery = mastery;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getRole_imges() {
        return role_imges;
    }

    public void setRole_imges(String role_imges) {
        this.role_imges = role_imges;
    }

    public String getBackground_imges() {
        return background_imges;
    }

    public void setBackground_imges(String background_imges) {
        this.background_imges = background_imges;
    }

    public String getElite_imges() {
        return elite_imges;
    }

    public void setElite_imges(String elite_imges) {
        this.elite_imges = elite_imges;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getNatura() {
        return natura;
    }

    public void setNatura(String natura) {
        this.natura = natura;
    }

    /**
     * JSON配置
     */
    public static final String JSON_CODE="code";//用于网站的JSON交互的代码状态名称
    public static final String JSON_MESSAGE="msg";//用于网站的JSON交互的错误信息名称
    public static final String JSON_DATA="data";//用于网站的JSON交互的数据信息名称
    public static final String JSON_TOTAL="count";//用于分页，记录总数

    /**
     * 文件上传配置
     */

    //获取允许上传的文件类型，根据kindEditor方法设计，需要考虑大小写。也可用于其它上传组件，现在为本站所用的上传模式
    public final static HashMap<String, String> EXT_MAP = new HashMap<String, String>(){
        {
            put("image", "gif,jpg,jpeg,png,bmp");
            put("flash", "swf,flv");
            put("media", "swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
            put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,chm");
        }
    };
    //角色头像上传配置
    public static final String ROLE_PROFILE_PICTURE_UPLOAD_URL= "role_profile_picture/";//保存角色头像头像的目录，用于上传的时候，如果放在static目录下，必须指明static下的绝对路径
    public static final String ROLE_PROFILE_PICTURE_URL="upload/role_profile_picture/";//保存角色头像头像的目录,用于展示的时候，不需要加上static
    //角色背景图片上传配置
    public static final String ROLE_BACKGROUND_PICTURE_UPLOAD_URL= "role_background/";//保存角色背景图片的目录，用于上传和删除的时候，如果放在static目录下，必须指明static下的绝对路径
    public static final String ROLE_BACKGROUND_PICTURE_URL= "upload/role_background/";//保存角色背景图片的目录,用于展示的时候，不需要加上static
    //精英化图片上传配置
    public static final String ROLE_ELITE_PICTURE_UPLOAD_URL= "role_elite/";//保存精英化图片的目录，用于上传和删除的时候，如果放在static目录下，必须指明static下的绝对路径
    public static final String ROLE_ELITE_PICTURE_URL= "upload/role_elite/";//保存精英化图片的目录,用于展示的时候，不需要加上static

}
