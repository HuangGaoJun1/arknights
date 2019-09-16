package com.akgs.arknights.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//表示所有的请求都是@ResponseBody
@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

    @GetMapping
    public Map<String,Object> Login(String username, String password){
        Map<String,Object> map=new HashMap<String,Object>();
        if(username.equals("user")&&password.equals("123456")){
            map.put("code",0);//自定义值：status。如果为1表示登录成功，如果为0表示登录失败
        }else{
            map.put("code",-1);
            map.put("msg","登录失败!");
        }
        return map;
    }
}
