package com.akgs.arknights.service;

import com.akgs.arknights.model.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    /**
     * 根据账户名和密码去数据库查询
     *
     * @param username 账户名
     * @param password 密码
     * @return null表示没有找到记录
     */
    Admin login(@Param(value = "username") String username, @Param(value = "password") String password);

    /**
     * 删除指定账户
     *
     * @param id 关键字
     * @return 删除了多少条记录
     */
    boolean deleteAdmin(Integer id, Integer adminId);
    /**
     * 返回所有的管理账户集合
     * @return 以List方式返回
     */
    List<Admin> getAdminList();
    /**
     * 修改账户的基本信息
     * @param admin id不能为空
     * @return 更改了多少条记录
     */
    boolean updateAdmin(Admin admin);

    /**
     * 修改密码
     * @param newPass
     * @param id
     * @return
     */
    boolean updatePassword(String newPass,Integer id);
    /**
     * 查找在数据库中和指定用户名重名的个数（用于账户编辑）
     * @param username
     * @param id
     * @return true表示存在重名账户，false表示不存在
     */
   boolean existsAdmin(String username,Integer id);

    /**
     * 判断账户名是否存在（用于创建新账户的时候）
     * @param username
     * @return true表示存在，false表示存在
     */
    boolean existsUsername(String username);
    /**
     * 将账户信息存入数据库
     * @param admin
     * @return true表示保存成功，false表示保存失败
     */
    boolean saveAdmin(Admin admin);
    /**
     * 根据标识符获取对应的管理账户对象
     * @param id
     * @return null表示没有找到
     */
    Admin getAdmin(Integer id);
}