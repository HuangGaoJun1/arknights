package com.akgs.arknights.web;

import com.akgs.arknights.model.User;
import com.akgs.arknights.service.UserService;
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

@Api(description = "LoginController 相关的api")
@RestController
public class UserLoginController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/api/userlogin")
    @ApiOperation(value="用户登录" ,notes = "用户登录操作")
    @ApiImplicitParam(name="username",value="登录账户",required = true,dataType = "User")

    public Map<String,Object> UserLogin(String username, String password, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        User user= userService.userlogin(username, password);
        if (username==""||password=="") {
            map.put("msg","登录失败，用户名或密码不能为空！");
        }else {
            if (user != null) {
                session.setAttribute("user", user);
                map.put("code", 1);//自定义值：status.如果为1表示登录成功,如果为0表示登录失败
            } else {
                map.put("code", 0);//自定义值：status.如果为1表示登录成功,如果为0表示登录失败
                map.put("msg", "登录失败:密码错误");
            }
        }
        return map;
    }
}
