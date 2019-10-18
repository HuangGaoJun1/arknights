package com.akgs.arknights.web;


import com.akgs.arknights.model.Admin;
import com.akgs.arknights.service.AdminService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(description = "AdminController 相关的api")

@RestController

public class AdminController {
	@Autowired
	private AdminService adminService;
	@ApiOperation(value="删除管理员" ,notes = "删除管理员操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@DeleteMapping(value = "/api/deleteAdmin")

	public Map<String,Object> DoDeleteAdmin( HttpSession session,Integer id) {
		Map<String,Object> map=new HashMap<String,Object>();
		Admin admin=(Admin)session.getAttribute("admin");
		if (adminService.deleteAdmin(id, admin.getId())){
			map.put("code","0");
			map.put("msg","删除成功！");
		}else {
			map.put("code","1");
			map.put("msg", "删除失败！");
		}
		return map;
	}

	@ApiOperation(value="修改管理员" ,notes = "修改管理员操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@PutMapping(value = "/api/updateAdmin")

	public Map<String,Object>doUpdateAdmin( @RequestBody Admin admin,HttpSession session) {
		Map<String,Object> map=new HashMap<String,Object>();
		Admin adminSession=(Admin)session.getAttribute("admin");
		if(adminSession.getUsername().length()==0){
			map.put("msg","账户名不能为空");
		}else if(adminSession.getName().length()==0){
			map.put("msg","名字不能为空");
		}else if(adminService.existsAdmin(admin.getUsername(),adminSession.getId())){
			map.put("msg","账户名不能有重名");
		}else {
			if(adminService.updateAdmin(admin)){
				admin.setUsername(admin.getUsername());
				admin.setName(admin.getName());
				map.put("code","0");
				map.put("msg","信息修改成功！");
			}else {
				map.put("code","1");
				map.put("msg","信息修改失败！账户名不能有重名");
			}
		}
		return map;
	}
	@ApiOperation(value="修改管理员密码" ,notes = "修改管理员密码操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@PutMapping(value = "/api/updatePassword")
	public Map<String, Object> doUpdatePassword(String oldPass, String newPass, String confirmPass, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Admin admin = (Admin) session.getAttribute("admin");
		if (adminService.login(admin.getUsername(), oldPass) != null) {//如果原密码正确
			if (newPass.equals(confirmPass)) {//如果新密码和确认密码相同
				//保存新密码
				adminService.updatePassword(newPass, admin.getId());
				map.put("code","0");
				map.put("myMessage", "密码修改成功");
			} else {//如果不相同
				map.put("code","1");
				map.put("myMessage", "密码修改失败：新密码和确认密码不一致");
			}
		} else {//如果原密码错误
			map.put("code","1");
			map.put("myMessage", "密码修改失败：原密码不正确");
		}
		return map;
	}
	@ApiOperation(value="添加管理员" ,notes = "添加管理员操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@PostMapping(value = "/api/addAdmin")

	public Map<String,Object> doAddAdmin( Admin admin,HttpSession session){
		Map<String,Object> map=new HashMap<String,Object>();
		Admin adminSession=(Admin)session.getAttribute("admin");
		adminSession.setUsername(adminSession.getUsername().trim());
		adminSession.setName(adminSession.getName().trim());
		adminSession.setPassword(adminSession.getPassword().trim());
		if(adminSession.getUsername().length()==0){
			map.put("msg","账户创建失败:账户名不能为空");
		}else if(adminSession.getName().length()==0){
			map.put("msg","账户创建失败:姓名不能为空");
		}else if(adminSession.getPassword().length()==0){
			map.put("msg","账户创建失败:密码不能为空");
		}if(adminService.existsUsername(admin.getUsername())){
			map.put("msg","账户创建失败:账户名已存在，请选择其他的账户名");
		}else{
			if(adminService.saveAdmin(admin)){
				map.put("code","0");
				map.put("msg","账户创建成功");
			}else{
				map.put("code","1");
				map.put("msg","账户创建失败");
			}
		}
		return map;
	}
	@ApiOperation(value="查询管理员" ,notes = "查询管理员操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@GetMapping(value = "/api/toManageAdmin")

	public Map<String,Object> toManageAdmin( Integer page,Integer limit){
		Map<String,Object> map=new HashMap<String,Object>();
		if(page==null||page.equals("")){
			page=1;
		}else if (page<1){
			page=1;
		}else if (page>adminService.maxPage(limit)) {
			page = adminService.maxPage(limit);
		}
		List list=adminService.getAdminList(page,limit);
		if (!list.isEmpty() && list!=null){
			map.put("code","0");
			map.put("data",list);
			map.put("page",page);
			map.put("count",list.size());
		}else {
			map.put("code", "1");
		}
		return map;
	}
	@ApiOperation(value="查询要修改管理员ID" ,notes = "查询要修改管理员ID操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@GetMapping(value = "/api/toGetAdmin")

	public Map<String,Object> toGetAdmin(Integer id){
		Map<String,Object> map=new HashMap<String,Object>();

			map.put("Admin",adminService.getAdmin(id));


		return map;
	}
}