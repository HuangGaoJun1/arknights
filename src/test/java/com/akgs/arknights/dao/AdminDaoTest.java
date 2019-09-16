package com.akgs.arknights.dao;

import com.akgs.arknights.ArknightsApplicationTests;

import com.akgs.arknights.model.Admin;
import org.junit.Test;

import javax.annotation.Resource;


public class AdminDaoTest extends ArknightsApplicationTests {
    @Resource
    private AdminDao adminDao;

    @Test
    public void testLogin(){
        Admin admin=adminDao.login("user","123456");
        System.out.println(admin);

    }
    @Test
    public void testDeleteAdmin(){
        System.out.println(adminDao.deleteAdmin(1));//0

    }
}
