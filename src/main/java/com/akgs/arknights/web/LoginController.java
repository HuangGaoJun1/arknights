package com.akgs.arknights.web;


import com.akgs.arknights.model.Admin;
import com.akgs.arknights.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


//表示所有的请求都是@ResponseBody
@Api(description = "LoginController 相关的api")
@RestController
public class LoginController {
    @Autowired
    private AdminService adminService;
    @GetMapping(value = "/api/login")
    @ApiOperation(value="管理员登录" ,notes = "管理员登录操作")
    @ApiImplicitParam(name="",value="",required = true,dataType = "")

    public Map<String,Object> Login(String username, String password, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        Admin admin= adminService.login(username, password);
        if (username==""||password=="") {
            map.put("msg","登录失败，用户名或密码不能为空！");
        }else {
            if (admin != null) {
                session.setAttribute("admin", admin);
                map.put("code", 1);//自定义值：status.如果为1表示登录成功,如果为0表示登录失败
            } else {
                map.put("code", 0);//自定义值：status.如果为1表示登录成功,如果为0表示登录失败
                map.put("msg", "登录失败:密码错误");
            }
        }
        return map;
    }


}
