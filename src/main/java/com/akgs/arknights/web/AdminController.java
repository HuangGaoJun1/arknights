package com.akgs.arknights.web;


import com.akgs.arknights.model.Admin;
import com.akgs.arknights.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@Api(description = "AdminController 相关的api")
@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;


	@ApiOperation(value="删除管理员" ,notes = "删除管理员操作")
	@ApiImplicitParam(name="id",value="删除管理员",required = true,dataType = "int")
	@DeleteMapping(value = "/api/deleteAdmin")
	public Map<String,Object> DoDeleteAdmin( HttpSession session,Integer id) {
		Map<String,Object> map=new HashMap<String,Object>();
		Admin admin=(Admin)session.getAttribute("admin");
		if (adminService.deleteAdmin(id, admin.getId())){
			map.put("msg","删除成功！");
		}else {
			map.put("msg", "删除失败！");
		}
		return map;
	}

	@ApiOperation(value="修改管理员" ,notes = "修改管理员操作")
	@ApiImplicitParam(name="id",value="修改管理员",required = true,dataType = "Admin")
	@PutMapping(value = "/api/updateAdmin")
	public Map<String,Object>doUpdateAdmin(Admin admin,HttpSession session) {
		Map<String,Object> map=new HashMap<String,Object>();
		Admin adminSession=(Admin)session.getAttribute("admin");
		if(adminSession.getUsername().length()==0){
			map.put("msg","账户名不能为空");
		}else if(adminSession.getName().length()==0){
			map.put("msg","名字不能为空");
		}else if(adminService.existsAdmin(admin.getUsername(),adminSession.getId())){
			map.put("msg","账户名不能有重名");
		}else{
			admin.setId(adminSession.getId());
			if(adminService.updateAdmin(admin)){
				admin.setUsername(admin.getUsername());
				admin.setName(admin.getName());
				map.put("msg","信息修改成功！");
			}else {
				map.put("msg","信息修改失败！账户名不能有重名");
			}
		}
		return map;
	}/**
	 oldPass 原密码
	 newPass 新密码
	 confirmPass 与新密码相同
 */
	@ApiOperation(value="修改管理员密码" ,notes = "修改管理员密码操作")
	@ApiImplicitParam(name="password",value="修改管理员密码",required = true,dataType = "int")
	@PutMapping (value = "/api/updatePassword")
	public Map<String,Object> doUpdatePassword(String oldPass, String newPass, String confirmPass, HttpSession session){
		Map<String,Object> map=new HashMap<String,Object>();
		Admin admin=(Admin)session.getAttribute("admin");
		if(adminService.login(admin.getUsername(), oldPass)!=null){//如果原密码正确
			if(newPass.length()==0){
				map.put("msg", "密码修改失败：新密码不能为空");
			}else if(newPass.equals(confirmPass)){//如果新密码和确认密码相同
				//保存新密码
				adminService.updatePassword(newPass, admin.getId());
				map.put("msg", "密码修改成功");
			}else{//如果不相同
				map.put("msg", "密码修改失败：新密码和确认密码不一致");
			}
		}else{//如果原密码错误
			map.put("msg", "密码修改失败：原密码不正确");
		}
		return map;
	}
	@ApiOperation(value="添加管理员" ,notes = "添加管理员操作")
	@ApiImplicitParam(name="addAdmin",value="添加管理员",required = true,dataType = "Admin")
	@PostMapping(value = "/api/addAdmin")

	public Map<String,Object> doAddAdmin( Admin admin){
		Map<String,Object> map=new HashMap<String,Object>();
		admin.setUsername(admin.getUsername().trim());
		admin.setName(admin.getName().trim());
		if(admin.getUsername().length()==0){
			map.put("msg","账户创建失败:账户名不能为空");
		}else if(admin.getName().length()==0){
			map.put("msg","账户创建失败:姓名不能为空");
		}else if(adminService.existsUsername(admin.getUsername())){
			map.put("msg","账户创建失败:账户名已存在，请选择其他的账户名");
		}else{
			if(adminService.saveAdmin(admin)){
				map.put("msg","账户创建成功");
			}else{
				map.put("msg","账户创建失败");
			}
		}
		return map;
	}
	@ApiOperation(value="查询管理员" ,notes = "查询管理员操作")
	@ApiImplicitParam(name="MangeAdmin",value="查询管理员",required = true,dataType = "Admin")
	@GetMapping(value = "/api/toManageAdmin")

	public Map<String,Object> toManageAdmin(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list",adminService.getAdminList());
		return map;
	}
	@RequestMapping(value = "/api/toGetAdmin")
	@GetMapping
	public Map<String,Object> toGetAdmin(Integer id){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list",adminService.getAdmin(id));
		return map;
	}
}