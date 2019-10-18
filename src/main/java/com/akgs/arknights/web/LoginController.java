package com.akgs.arknights.web;


import com.akgs.arknights.model.Admin;
import com.akgs.arknights.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Api(description = "LoginController 相关的api")
@RestController
public class LoginController {
    @Autowired
    private AdminService adminService;
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @GetMapping(value = "/api/login")
    @ApiOperation(value="管理员登录" ,notes = "管理员登录操作")
    public Map<String,Object> Login( String username, String password, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        Admin admin= adminService.login(username, password);
        if (username==""||password=="") {
            map.put("msg","登录失败，用户名或密码不能为空！");
        }else {
            if (admin != null) {
                session.setAttribute("admin", admin);
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
    @GetMapping(value = "/api/logout")
    @ApiOperation(value="管理员注销" ,notes = "管理员注销操作")
    public Map<String,Object> Logout( HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        if(session!=null) {
            Admin admin = (Admin) session.getAttribute("admin");//从当前session中获取用户信息
            session.invalidate();//关闭session
            map.put("code","1");
            map.put("msg","注销成功！");
        }
        return map;
    }

}
