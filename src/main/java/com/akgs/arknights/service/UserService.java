package com.akgs.arknights.service;

import com.akgs.arknights.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     */
    User userlogin(@Param(value = "username") String username, @Param(value = "password") String password);
    /**
     * 修改账户的基本信息
     * @param user id不能为空
     * @return 更改了多少条记录
     */
    boolean updateUser(User user);
    /**
     * 返回所有的管理账户集合
     * @return 以List方式返回
     */
    List<User> getUserList();

    /**
     * 删除指定账户
     * @param id 关键字
     * @return 删除了多少条记录
     */
    boolean deleteUser(Integer id,Integer userId);

    /**
     * 修改密码
     * @param newPass
     * @param id
     * @return
     */
    boolean updatePassword(String newPass,Integer id);
    /**
     * 查找在数据库中和指定用户名重名的个数
     * @param username
     * @param id
     * @return 返回重名的个数，0表示不重名
     */
    boolean existsUser(@Param(value = "username")String username, @Param(value = "id")Integer id);

    /**
     * 查找在数据库中和指定用户名重名的个数
     * @param username
     * @return 返回重名的个数，0表示不重名
     */
    boolean existsUserUsername(@Param(value = "username")String username);
    /**
     * 注册
     * @param user
     * @return 添加了多少条记录
     */
    boolean saveUser(User user);
    /**
     * 根据标识符获取对应的管理账户对象
     * @param id
     * @return null表示没有找到
     */
        User getUser(Integer id);
}
