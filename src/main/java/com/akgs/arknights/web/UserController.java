package com.akgs.arknights.web;


import com.akgs.arknights.model.User;
import com.akgs.arknights.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api(description = "UserController 相关的api")
@RestController
public class UserController {
	@Autowired
	private UserService userService;


	@ApiOperation(value="删除用户" ,notes = "删除用户操作")
	@ApiImplicitParam(name="id",value="删除用户",required = true,dataType = "User")
	@DeleteMapping(value = "/api/deleteUser")
	public Map<String,Object> DoDeleteUser (HttpSession session,Integer id) {
		Map<String,Object> map=new HashMap<String,Object>();
		User user=(User)session.getAttribute("User");
		if (userService.deleteUser(id, user.getId())){
			map.put("msg","删除成功！");
		}else {
			map.put("msg", "删除失败！");
		}
		return map;
	}

	@ApiOperation(value="修改用户信息" ,notes = "修改用户操作")
	@ApiImplicitParam(name="id",value="修改用户",required = true,dataType = "User")
	@PutMapping(value = "/api/updateUser")
	public Map<String,Object>doUpdateUser(User user,HttpSession session) {
		Map<String,Object> map=new HashMap<String,Object>();
		User userSession=(User) session.getAttribute("admin");
		if(userSession.getUsername().length()==0){
			map.put("msg","账户名不能为空");
		}else if(userSession.getName().length()==0){
			map.put("msg","名字不能为空");
		}else if(userService.existsUser(user.getUsername(),userSession.getId())){
			map.put("msg","账户名不能有重名");
		}else{
			user.setId(userSession.getId());
			if(userService.updateUser(user)){
				user.setUsername(user.getUsername());
				user.setName(user.getName());
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
	@ApiOperation(value="修改用户密码" ,notes = "修改用户密码操作")
	@ApiImplicitParam(name="password",value="修改用户密码",required = true,dataType = "int")
	@PutMapping (value = "/api/updateUserPassword")
	public Map<String,Object> doUpdatePassword(String oldPass, String newPass, String confirmPass, HttpSession session){
		Map<String,Object> map=new HashMap<String,Object>();
		User user=(User) session.getAttribute("admin");
		if(userService.userlogin(user.getUsername(), oldPass)!=null){//如果原密码正确
			if(newPass.length()==0){
				map.put("msg", "密码修改失败：新密码不能为空");
			}else if(newPass.equals(confirmPass)){//如果新密码和确认密码相同
				//保存新密码
				userService.updatePassword(newPass, user.getId());
				map.put("msg", "密码修改成功");
			}else{//如果不相同
				map.put("msg", "密码修改失败：新密码和确认密码不一致");
			}
		}else{//如果原密码错误
			map.put("msg", "密码修改失败：原密码不正确");
		}
		return map;
	}
	@ApiOperation(value="注册用户" ,notes = "注册用户操作")
	@ApiImplicitParam(name="addUser",value="注册用户",required = true,dataType = "User")
	@PostMapping(value = "/api/addUser")

	public Map<String,Object> doAddUser( User user){
		Map<String,Object> map=new HashMap<String,Object>();
		user.setUsername(user.getUsername().trim());
		user.setName(user.getName().trim());
		if(user.getUsername().length()==0){
			map.put("msg","账户注册失败:账户名不能为空");
		}else if(user.getName().length()==0){
			map.put("msg","账户注册失败:姓名不能为空");
		}else if(userService.existsUserUsername(user.getUsername())){
			map.put("msg","账户注册失败:账户名已存在，请选择其他的账户名");
		}else{
			if(userService.saveUser(user)){
				map.put("msg","账户注册成功");
			}else{
				map.put("msg","账户注册失败");
			}
		}
		return map;
	}
	@ApiOperation(value="查询用户" ,notes = "查询用户操作")
	@ApiImplicitParam(name="MangeUser",value="查询用户",required = true,dataType = "User")
	@GetMapping(value = "/api/toManageUser")

	public Map<String,Object> toManageUser(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list",userService.getUserList());
		return map;
	}
	@RequestMapping(value = "/api/toGetUser")
	@GetMapping
	public Map<String,Object> toGetUser(Integer id){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list",userService.getUser(id));
		return map;
	}
}