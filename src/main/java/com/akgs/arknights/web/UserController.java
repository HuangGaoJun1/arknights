package com.akgs.arknights.web;


import com.akgs.arknights.model.Admin;
import com.akgs.arknights.model.User;
import com.akgs.arknights.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "UserController 相关的api")
@RestController
public class UserController {
	@Autowired
	private UserService userService;


	@ApiOperation(value="删除用户" ,notes = "删除用户操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@DeleteMapping(value = "/api/deleteUser")
	public Map<String,Object> DoDeleteUser (HttpSession session,Integer id) {
		Map<String,Object> map=new HashMap<String,Object>();
		Admin adminSession=(Admin)session.getAttribute("admin");
		if (userService.deleteUser(id)){
			map.put("code","0");
			map.put("msg","删除成功！");
		}else {
			map.put("code","1");
			map.put("msg", "删除失败！");
		}
		return map;
	}

	@ApiOperation(value="修改用户信息" ,notes = "修改用户操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@PutMapping(value = "/api/updateUser")
	public Map<String,Object>doUpdateUser(@RequestBody User user) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(user.getUsername().length()==0){
			map.put("msg","账户名不能为空！");
		}else if(user.getName().length()==0){
			map.put("msg","名字不能为空！");
		}else if (user.getPhone().length()==0){
			map.put("msg","手机号码不能为空！");
		}else if (user.getMail().length()==0){
			map.put("msg","邮箱不能为空！");
		}else if(userService.existsUser(user.getUsername(),user.getId())){
			map.put("msg","账户名不能有重名");
		}else{
			if(userService.updateUser(user)){
				user.setUsername(user.getUsername());
				user.setName(user.getName());
				user.setPhone(user.getPhone());
				user.setMail(user.getMail());
				map.put("code","0");
				map.put("msg","信息修改成功！");
			}else {
				map.put("code","1");
				map.put("msg","信息修改失败！账户名不能有重名");
			}
		}
		return map;
	}
	@ApiOperation(value="修改用户密码" ,notes = "修改用户密码操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@PutMapping(value = "/api/updateUserPassword")
	public Map<String, Object> doUpdateUserPassword(String oldPass, String newPass, String confirmPass, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		User usersession=(User)session.getAttribute("user");
		if (userService.userlogin(usersession.getUsername(), oldPass) != null) {//如果原密码正确
			if (newPass.equals(confirmPass)) {//如果新密码和确认密码相同
				//保存新密码
				userService.updatePassword(newPass, usersession.getId());
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
	@ApiOperation(value="注册用户" ,notes = "注册用户操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@PostMapping(value = "/api/addUser")
	public Map<String,Object> doAddUser(  User user,String confirmPass){
		Map<String,Object> map=new HashMap<String,Object>();
		user.setUsername(user.getUsername().trim());
		user.setName(user.getName().trim());
		user.setPassword(user.getPassword().trim());
		user.setPhone(user.getPhone().trim());
		user.setMail(user.getMail().trim());
		if(user.getUsername().length()==0) {
			map.put("msg", "账户注册失败:账户名不能为空");
		}else if (user.getPassword().length()==0){
			map.put("msg","账户注册失败:密码不能为空");
		}else if(user.getName().length()==0){
			map.put("msg","账户注册失败:姓名不能为空");
		}else if(userService.existsUserUsername(user.getUsername())){
			map.put("msg","账户注册失败:账户名已存在，请选择其他的账户名");
		}else if(user.getPassword().equals(confirmPass)){//如果新密码和确认密码相同
			if(userService.saveUser(user)){
				map.put("code","0");
				map.put("msg","账户注册成功");
			}
		}else{
				map.put("code","1");
				map.put("msg","账户注册失败");

		}
		return map;
	}
	@ApiOperation(value="查询用户" ,notes = "查询用户操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@GetMapping(value = "/api/toManageUser")

	public Map<String,Object> toManageUser( Integer page,Integer limit){
		Map<String,Object> map=new HashMap<String,Object>();
		if(page==null ||page.equals("")){
			page=1;
		}else if (page>=userService.maxPage(limit)){
			page=userService.maxPage(limit);
		}else if (page<=1) {
			page = 1;
		}
		List list=userService.getUserList(page,limit);
		if (!list.isEmpty() && list!=null){
			map.put("code","0");
			map.put("data",list);
			map.put("page",page);
			map.put("count",list.size());
		}else {
			map.put("code","1");
		}
		return map;
	}
	@ApiOperation(value="查询要修改用户ID" ,notes = "查询要修改用户ID操作")
	@CrossOrigin(origins = "*",allowCredentials = "true")
	@GetMapping(value = "/api/toGetUser")

	public Map<String,Object> toGetUser(Integer id){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list",userService.getUser(id));
		return map;
	}
}