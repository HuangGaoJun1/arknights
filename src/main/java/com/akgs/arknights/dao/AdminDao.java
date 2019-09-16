package com.akgs.arknights.dao;
import com.akgs.arknights.model.Admin;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AdminDao {
   /**
            * 根据账户名和密码去数据库查询
     * @param username 账户名
     * @param password 密码
     * @return null表示没有找到记录
     */
    Admin login(@Param(value = "username") String username, @Param(value = "password") String password);
    /**
     * 修改账户的基本信息
     * @param admin id不能为空
     * @return 更改了多少条记录
     */
    int updateAdmin(Admin admin);
    /**
     * 返回所有的管理账户集合
     * @return 以List方式返回
     */
    List<Admin> getAdminList();

    /**
     * 删除指定账户
     * @param id 关键字
     * @return 删除了多少条记录
     */
    int deleteAdmin(Integer id);

}
