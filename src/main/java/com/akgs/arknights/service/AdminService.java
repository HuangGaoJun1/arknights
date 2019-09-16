package com.akgs.arknights.service;

import com.akgs.arknights.model.Admin;

public interface AdminService {
    /**
     * 根据账户名和密码去数据库查询
     * @param username 账户名
     * @param password 密码
     * @return null表示没有找到记录
     */
    Admin login(String username, String password, Integer id);
    /**
     * 删除指定账户
     * @param id 关键字
     * @return 删除了多少条记录
     */
    int deleteAdmin(Integer id);
}
