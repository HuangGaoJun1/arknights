package com.akgs.arknights.web;

import com.akgs.arknights.model.User;
import com.akgs.arknights.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(description  = "UserLogin借口模块")
public class UserLoginController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "User登录")
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @GetMapping(value = "/api/userlogin")
    public Map<String,Object> UserLogin( String username, String password, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        User user= userService.userlogin(username, password);
        if (username==""||password=="") {
            map.put("msg","登录失败，用户名或密码不能为空！");
        }else {
            if (user != null) {
                session.setAttribute("user", user);
                map.put("code", 1);//自定义值：status.如果为1表示登录成功,如果为0表示登录失败
                map.put("msg", "登录成功！");
            } else {
                map.put("code", 0);//自定义值：status.如果为1表示登录成功,如果为0表示登录失败
                map.put("msg", "登录失败:密码错误");
            }
        }
        return map;
    }

    @CrossOrigin(origins = "*",allowCredentials = "true")
    @GetMapping(value = "/api/userlogout")
    @ApiOperation(value="用户注销")
    public Map<String,Object> UserLogout( HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        if(session!=null) {
            User user=(User)session.getAttribute("user");//从当前session中获取用户信息
            session.invalidate();//关闭session
            map.put("code","1");
            map.put("msg","注销成功！");
        }
        return map;
    }
}
