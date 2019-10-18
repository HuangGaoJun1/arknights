package com.akgs.arknights.web;
import com.akgs.arknights.model.Admin;
import com.akgs.arknights.model.Arknig_enemy;
import com.akgs.arknights.service.Arknig_enemyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(description = "Arknig_enemyController 相关的api")
@RestController
public class Arknig_enemyController {

    @Autowired
    private Arknig_enemyService arknig_enemyService;

    @ApiOperation(value="查询敌方图鉴" ,notes = "查询敌方图鉴")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @GetMapping(value = "/api/ManageArknig_enemy_List")

    public Map<String,Object> toManageArknig_enemy_List(){
        Map<String,Object> map=new HashMap<String,Object>();
        List list=arknig_enemyService.getArknig_enemy_List();
        if (!list.isEmpty() && list!=null){
            map.put("code","0");
            map.put("data",list);
            map.put("count",list.size());
        }else {
            map.put("code", "1");
        }
        return map;
    }

    @ApiOperation(value="根据分组，攻击方式查询敌方图鉴" ,notes = "根据分组，攻击方式查询敌方图鉴")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @GetMapping(value = "/api/getArknig_enemy")

    public Map<String,Object> togetArknig_enemy(Arknig_enemy arknig_enemy){
        Map<String,Object> map=new HashMap<String,Object>();
        List list=arknig_enemyService.getArknig_enemy(arknig_enemy.getGrouping(),arknig_enemy.getAttackmode());
        if (!list.isEmpty() && list!=null){
            map.put("code","0");
            map.put("data",list);
            map.put("count",list.size());
        }else {
            map.put("code", "1");
        }
        return map;
    }
    @ApiOperation(value="删除敌方图鉴" ,notes = "删除敌方图鉴操作")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @DeleteMapping(value = "/api/deleteArknig_enemy")
    public Map<String,Object> DoDeleteArknig_enemy(HttpSession session,Integer id) {
        Map<String,Object> map=new HashMap<String,Object>();
        if (arknig_enemyService.deleteArknig_enemy(id)){
            map.put("code","0");
            map.put("msg","删除成功！");
        }else {
            map.put("code","1");
            map.put("msg", "删除失败！");
        }
        return map;
    }
    @ApiOperation(value="修改敌方图鉴" ,notes = "修改敌方图鉴操作")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PutMapping(value = "/api/updateArknig_enemy")
    public Map<String,Object>doUpdateArknig_enemy( @RequestBody  Arknig_enemy arknig_enemy) {
        Map<String,Object> map=new HashMap<String,Object>();
        if(arknig_enemy.getName().length()==0){
            map.put("msg","名字不能为空");
        }else if(arknig_enemy.getAttackmode().length()==0){
            map.put("msg","攻击方式不能为空");
        }else if(arknig_enemy.getGrouping().length()==0){
            map.put("msg","分组不能为空");
        }else if(arknig_enemyService.existsArknig_enemy(arknig_enemy.getName())){
            map.put("msg","名字不能有重名");
        }else{
            if (arknig_enemyService.updateArknig_enemy(arknig_enemy)){
                arknig_enemy.setName(arknig_enemy.getName());
                arknig_enemy.setAttackmode(arknig_enemy.getAggressivity());
                arknig_enemy.setGrouping(arknig_enemy.getGrouping());
                arknig_enemy.setAggressivity(arknig_enemy.getAggressivity());
                arknig_enemy.setCategory(arknig_enemy.getCategory());
                arknig_enemy.setDefensive(arknig_enemy.getDefensive());
                arknig_enemy.setDurable(arknig_enemy.getDurable());
                arknig_enemy.setImges(arknig_enemy.getImges());
                arknig_enemy.setIntroduce(arknig_enemy.getIntroduce());
                arknig_enemy.setNumber(arknig_enemy.getNumber());
                arknig_enemy.setRemarks(arknig_enemy.getRemarks());
                arknig_enemy.setResistance(arknig_enemy.getResistance());
                map.put("code","0");
                map.put("msg","信息修改成功！");
            }else {
                map.put("code","1");
                map.put("msg","信息修改失败!");
            }
        }
        return map;
    }
    @ApiOperation(value="添加敌方图鉴" ,notes = "添加敌方图鉴操作")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PostMapping(value = "/api/addArknig_enemy")

    public Map<String,Object> doAddArknig_enemy( @RequestBody Arknig_enemy arknig_enemy){
        Map<String,Object> map=new HashMap<String,Object>();
        arknig_enemy.setName(arknig_enemy.getName().trim());
        arknig_enemy.setAttackmode(arknig_enemy.getAttackmode().trim());
        arknig_enemy.setGrouping(arknig_enemy.getGrouping().trim());
        arknig_enemy.setResistance(arknig_enemy.getResistance().trim());
        arknig_enemy.setRemarks(arknig_enemy.getRemarks().trim());
        arknig_enemy.setNumber(arknig_enemy.getNumber().trim());
        arknig_enemy.setIntroduce(arknig_enemy.getIntroduce().trim());
        arknig_enemy.setImges(arknig_enemy.getImges().trim());
        arknig_enemy.setDurable(arknig_enemy.getDurable().trim());
        arknig_enemy.setDefensive(arknig_enemy.getDefensive().trim());
        arknig_enemy.setCategory(arknig_enemy.getCategory().trim());
        arknig_enemy.setAggressivity(arknig_enemy.getAggressivity());
        arknig_enemy.setWeight(arknig_enemy.getWeight());
        if(arknig_enemy.getGrouping().length()==0){
            map.put("msg","敌方图鉴创建失败:分组不能为空");
        }else if(arknig_enemy.getName().length()==0){
            map.put("msg","敌方图鉴创建失败:名字不能为空");
        }else if(arknig_enemy.getAttackmode().length()==0){
            map.put("msg","敌方图鉴创建失败:攻击方式不能为空");
        }else if(arknig_enemyService.existsname(arknig_enemy.getName())){
            map.put("msg","敌方图鉴创建失败:名称已存在，请选择其他的名称");
        }else{
            if(arknig_enemyService.saveArknig_enemy(arknig_enemy)){
                map.put("code","0");
                map.put("msg","敌方图鉴创建成功");
            }else{
                map.put("code","0");
                map.put("msg","敌方图鉴创建失败");
            }
        }
        return map;
    }
    @ApiOperation(value="查询要修改敌方图鉴ID" ,notes = "查询要修改敌方图鉴ID操作")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @GetMapping(value = "/api/toGetArknig_enemy")

    public Map<String,Object> toGetArknig_enemy(Integer id){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("Arknig_enemy",arknig_enemyService.togetArknig_enemy(id));
        return map;
    }
}
