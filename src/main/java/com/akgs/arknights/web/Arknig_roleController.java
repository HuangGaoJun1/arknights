package com.akgs.arknights.web;

import com.akgs.arknights.config.JsonCode;
import com.akgs.arknights.model.Arknig_role;
import com.akgs.arknights.service.Arknig_roleService;

import com.akgs.arknights.util.file.MyFileOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "Arknig_roleController 相关的api")
@RestController
public class Arknig_roleController {

    @Autowired
    private Arknig_roleService arknig_roleService;

    @Value("${myFile.uploadFolder}")
    private String uploadFolder;//上传路径

    @ApiOperation(value="职业ID查询我方图鉴" ,notes = "职业ID查询我方图鉴")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @GetMapping(value = "/api/getArknig_opnList")

    public Map<String,Object> getArknig_opnList(Integer opn_id){
        Map<String,Object> map=new HashMap<String,Object>();
        List list=arknig_roleService.getArknig_opnList(opn_id);
        if (!list.isEmpty() &&list!=null){
            map.put("code","0");
            map.put("data",list);
            map.put("count",list.size());
        }else {
            map.put("code", "1");
        }
        return map;
    }


    @ApiOperation(value="招募工具" ,notes = "招募工具")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @GetMapping(value = "/api/getArknig_rolelList")

    public Map<String,Object> getArknig_roleList(Arknig_role arknig_role){
        Map<String,Object> map=new HashMap<String,Object>();
        List list=arknig_roleService.getArknig_roleList(arknig_role);
        if (!list.isEmpty() && list!=null){
            map.put("code","0");
            map.put("data",list);
            map.put("count",list.size());
        }else {
            map.put("code", "1");
        }
        return map;
    }
    @ApiOperation(value = "上传指定角色的头像", notes = "根据id的值上传指定角色的头像")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PostMapping("api/roleuploadPhoto/{role_id}")
    public Map<String, Object> roleuploadPhoto(@PathVariable Integer role_id,MultipartFile file) throws FileNotFoundException {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Arknig_role.JSON_CODE, JsonCode.ERROR.getValue());//默认失败
        if(!file.isEmpty()){
            Arknig_role arknig_role=arknig_roleService.getArknig_role(role_id);//获取账户对象
            if(arknig_role!=null){//如果该账户存在，则执行上传
                //String basepath=ClassUtils.getDefaultClassLoader().getResource("").getPath();//获取项目的根目录(物理路径)，注意不能用JSP那套获取根目录，因为spring boot的tomcat为内置，每次都变
                String basepath=uploadFolder;
                String filePath=basepath+Arknig_role.ROLE_PROFILE_PICTURE_UPLOAD_URL;//获取图片上传后保存的物理路径
                MyFileOperator.createDir(filePath);//创建存储目录
                String fileName=file.getOriginalFilename();//获取文件名
                String extensionName=MyFileOperator.getExtensionName(fileName);//获取文件扩展名
                fileName=role_id+"."+extensionName;//根据id重新生成文件名
                try {
                    file.transferTo(new File(filePath+fileName));
                    if(!fileName.equals(arknig_role.getRole_imges())){//如果新上传的文件名和原来的不一样，则需要删除原来的文件；如果一样则直接覆盖，不需要处理
                        MyFileOperator.deleteFile(filePath+arknig_role.getRole_imges());//删除原文件
                    }
                    arknig_role.setRole_id(role_id);
                    arknig_role.setRole_imges(fileName);
                    arknig_roleService.updateArknig_role(arknig_role);//将新的图片信息存入数据库
                    map.put(Arknig_role.JSON_CODE, JsonCode.SUCCESS.getValue());
                    map.put("code","0");
                    map.put(Arknig_role.JSON_MESSAGE, "上传成功");
                } catch (IOException e) {
                    map.put("code","1");
                    map.put(Arknig_role.JSON_MESSAGE, "头像上传失败："+e.getMessage());
                }
            }
        }else{
            map.put("code","1");
            map.put(Arknig_role.JSON_MESSAGE, "上传失败：请先选择文件");
        }
        return map;
    }
    @ApiOperation(value = "上传指定角色的背景图", notes = "根据id的值上传指定角色的背景图")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PostMapping("api/rolebackuploadPhoto/{role_id}")
    public Map<String, Object> roleupbackloadPhoto(@PathVariable Integer role_id,MultipartFile file) throws FileNotFoundException {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Arknig_role.JSON_CODE, JsonCode.ERROR.getValue());//默认失败
        if(!file.isEmpty()){
            Arknig_role arknig_role=arknig_roleService.getArknig_role(role_id);//获取账户对象
            if(arknig_role!=null){//如果该账户存在，则执行上传
                //String basepath=ClassUtils.getDefaultClassLoader().getResource("").getPath();//获取项目的根目录(物理路径)，注意不能用JSP那套获取根目录，因为spring boot的tomcat为内置，每次都变
                String basepath=uploadFolder;
                String filePath=basepath+Arknig_role.ROLE_BACKGROUND_PICTURE_UPLOAD_URL;//获取图片上传后保存的物理路径
                MyFileOperator.createDir(filePath);//创建存储目录
                String fileName=file.getOriginalFilename();//获取文件名
                String extensionName=MyFileOperator.getExtensionName(fileName);//获取文件扩展名
                fileName=role_id+"."+extensionName;//根据id重新生成文件名
                try {
                    file.transferTo(new File(filePath+fileName));
                    if(!fileName.equals(arknig_role.getBackground_imges())){//如果新上传的文件名和原来的不一样，则需要删除原来的文件；如果一样则直接覆盖，不需要处理
                        MyFileOperator.deleteFile(filePath+arknig_role.getBackground_imges());//删除原文件
                    }
                    arknig_role.setRole_id(role_id);
                    arknig_role.setBackground_imges(fileName);
                    arknig_roleService.updateArknig_role(arknig_role);//将新的图片信息存入数据库
                    map.put(Arknig_role.JSON_CODE, JsonCode.SUCCESS.getValue());
                    map.put("code","0");
                    map.put(Arknig_role.JSON_MESSAGE, "上传成功");
                } catch (IOException e) {
                    map.put("code","1");
                    map.put(Arknig_role.JSON_MESSAGE, "头像上传失败："+e.getMessage());
                }
            }
        }else{
            map.put("code","1");
            map.put(Arknig_role.JSON_MESSAGE, "上传失败：请先选择文件");
        }
        return map;
    }
    @ApiOperation(value = "上传指定角色的精英化图", notes = "根据id的值上传指定角色的精英化图")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PostMapping("api/roleeliteuploadPhoto/{role_id}")
    public Map<String, Object> roleupeliteloadPhoto(@PathVariable Integer role_id,MultipartFile file) throws FileNotFoundException {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(Arknig_role.JSON_CODE, JsonCode.ERROR.getValue());//默认失败
        if(!file.isEmpty()){
            Arknig_role arknig_role=arknig_roleService.getArknig_role(role_id);//获取账户对象
            if(arknig_role!=null){//如果该账户存在，则执行上传
                //String basepath=ClassUtils.getDefaultClassLoader().getResource("").getPath();//获取项目的根目录(物理路径)，注意不能用JSP那套获取根目录，因为spring boot的tomcat为内置，每次都变
                String basepath=uploadFolder;
                String filePath=basepath+Arknig_role.ROLE_ELITE_PICTURE_UPLOAD_URL;//获取图片上传后保存的物理路径
                MyFileOperator.createDir(filePath);//创建存储目录
                String fileName=file.getOriginalFilename();//获取文件名
                String extensionName=MyFileOperator.getExtensionName(fileName);//获取文件扩展名
                fileName=role_id+"."+extensionName;//根据id重新生成文件名
                try {
                    file.transferTo(new File(filePath+fileName));
                    if(!fileName.equals(arknig_role.getElite_imges())){//如果新上传的文件名和原来的不一样，则需要删除原来的文件；如果一样则直接覆盖，不需要处理
                        MyFileOperator.deleteFile(filePath+arknig_role.getElite_imges());//删除原文件
                    }
                    arknig_role.setRole_id(role_id);
                    arknig_role.setElite_imges(fileName);
                    arknig_roleService.updateArknig_role(arknig_role);//将新的图片信息存入数据库
                    map.put(Arknig_role.JSON_CODE, JsonCode.SUCCESS.getValue());
                    map.put("code","0");
                    map.put(Arknig_role.JSON_MESSAGE, "上传成功");
                } catch (IOException e) {
                    map.put("code","1");
                    map.put(Arknig_role.JSON_MESSAGE, "头像上传失败："+e.getMessage());
                }
            }
        }else{
            map.put("code","1");
            map.put(Arknig_role.JSON_MESSAGE, "上传失败：请先选择文件");
        }
        return map;
    }
    @ApiOperation(value = "移除角色头像", notes = "根据id的值删除角色头像")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PatchMapping("api/removeRoleProfilePicture/{role_id}")
    public Map<String, Object> removeCustomersProfilePicture(@PathVariable("role_id")Integer role_id){
        Map<String, Object> map=new HashMap<String, Object>();
        String basePath=uploadFolder;
        arknig_roleService.removeRoleProfilePicture(role_id,basePath);
        map.put(Arknig_role.JSON_CODE, JsonCode.SUCCESS.getValue());//默认失败
        return map;
    }

    @ApiOperation(value = "删除角色信息", notes = "根据id的值删除角色信息")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @DeleteMapping("api/deleteRole")
    public Map<String, Object> deleteRole(Integer role_id){
        Map<String, Object> map=new HashMap<String, Object>();
        if (arknig_roleService.deleteArknig_role(role_id)){
            map.put("code","0");
            map.put("msg","删除成功");
        }else{
            map.put("code","1");
            map.put("msg","删除失败");
        }
        return map;
    }

    @ApiOperation(value="修改角色信息" ,notes = "修改角色信息操作")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PutMapping(value = "/api/updateArknig_role")
    public Map<String,Object>doUpdateArknig_role( @RequestBody  Arknig_role arknig_role) {
        Map<String,Object> map=new HashMap<String,Object>();
        if(arknig_role.getName().length()==0){
            map.put("msg","名字不能为空");
        }else if(arknig_role.getOpn_id()==0){
            map.put("msg","职业ID不能为空");
        }else if(arknig_role.getPosition()==0){
            map.put("msg","位置不能为空");
        }else if(arknig_roleService.existsArknig_role(arknig_role.getName(),arknig_role.getRole_id())){
            map.put("msg","名字不能有重名");
        }else{
            if (arknig_roleService.updateArknig_role(arknig_role)){
                arknig_role.setName(arknig_role.getName());
                arknig_role.setOpn_id(arknig_role.getOpn_id());
                arknig_role.setLabel_id(arknig_role.getLabel_id());
                arknig_role.setRole_imges(arknig_role.getRole_imges());
                arknig_role.setNatura(arknig_role.getNatura());
                arknig_role.setPosition(arknig_role.getPosition());
                arknig_role.setStar(arknig_role.getStar());
                arknig_role.setElite(arknig_role.getElite());
                arknig_role.setElite_imges(arknig_role.getElite_imges());
                arknig_role.setCamp(arknig_role.getCamp());
                arknig_role.setCamp_imges(arknig_role.getCamp_imges());
                arknig_role.setGender(arknig_role.getGender());
                arknig_role.setPainter(arknig_role.getPainter());
                arknig_role.setCV(arknig_role.getCV());
                arknig_role.setCharact(arknig_role.getCharact());
                arknig_role.setAccessWay(arknig_role.getAccessWay());
                arknig_role.setExperience(arknig_role.getExperience());
                arknig_role.setBirth(arknig_role.getBirth());
                arknig_role.setBirthday(arknig_role.getBirthday());
                arknig_role.setRace(arknig_role.getRace());
                arknig_role.setHeight(arknig_role.getHeight());
                arknig_role.setOreDisInfe(arknig_role.getOreDisInfe());
                arknig_role.setObjCurrVitae(arknig_role.getObjCurrVitae());
                arknig_role.setFunction(arknig_role.getFunction());
                arknig_role.setMastery(arknig_role.getMastery());
                arknig_role.setBrief(arknig_role.getBrief());
                arknig_role.setBackground_imges(arknig_role.getBackground_imges());
                map.put("code","0");
                map.put("msg","信息修改成功！");
            }else {
                map.put("code","1");
                map.put("msg","信息修改失败!");
            }
        }
        return map;
    }
    @ApiOperation(value="添加角色" ,notes = "添加角色")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @PostMapping(value = "/api/addArknig_role")

    public Map<String,Object> doAddArknig_role( @RequestBody Arknig_role arknig_role){
        Map<String,Object> map=new HashMap<String,Object>();
        arknig_role.setName(arknig_role.getName().trim());
        arknig_role.setOpn_id(arknig_role.getOpn_id());
        arknig_role.setLabel_id(arknig_role.getLabel_id().trim());
        arknig_role.setRole_imges(arknig_role.getRole_imges().trim());
        arknig_role.setNatura(arknig_role.getNatura().trim());
        arknig_role.setPosition(arknig_role.getPosition());
        arknig_role.setStar(arknig_role.getStar());
        arknig_role.setElite(arknig_role.getElite());
        arknig_role.setElite_imges(arknig_role.getElite_imges().trim());
        arknig_role.setCamp(arknig_role.getCamp().trim());
        arknig_role.setCamp_imges(arknig_role.getCamp_imges().trim());
        arknig_role.setGender(arknig_role.getGender());
        arknig_role.setPainter(arknig_role.getPainter().trim());
        arknig_role.setCV(arknig_role.getCV().trim());
        arknig_role.setCharact(arknig_role.getCharact().trim());
        arknig_role.setAccessWay(arknig_role.getAccessWay().trim());
        arknig_role.setExperience(arknig_role.getExperience().trim());
        arknig_role.setBirth(arknig_role.getBirth().trim());
        arknig_role.setBirthday(arknig_role.getBirthday().trim());
        arknig_role.setRace(arknig_role.getRace().trim());
        arknig_role.setHeight(arknig_role.getHeight().trim());
        arknig_role.setOreDisInfe(arknig_role.getOreDisInfe().trim());
        arknig_role.setObjCurrVitae(arknig_role.getObjCurrVitae().trim());
        arknig_role.setFunction(arknig_role.getFunction().trim());
        arknig_role.setMastery(arknig_role.getMastery().trim());
        arknig_role.setBrief(arknig_role.getBrief().trim());
        arknig_role.setBackground_imges(arknig_role.getBackground_imges().trim());
        if(arknig_role.getName().length()==0){
            map.put("msg","名字不能为空");
        }else if(arknig_role.getOpn_id()==0){
            map.put("msg","职业ID不能为空");
        }else if(arknig_role.getPosition()==0){
            map.put("msg","位置不能为空");
        }else if(arknig_roleService.existsname(arknig_role.getName())){
            map.put("msg","名字不能有重名");
        }else{
            if(arknig_roleService.saveArknig_role(arknig_role)){
                map.put("code","0");
                map.put("msg","角色创建成功");
            }else{
                map.put("code","0");
                map.put("msg","角色创建失败");
            }
        }
        return map;
    }
}
