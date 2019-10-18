package com.akgs.arknights.dao;
import org.apache.ibatis.annotations.Param;
import com.akgs.arknights.model.User;
import java.util.List;

public interface UserDao {
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
    Integer updateUser(User user);
 /*
     * 返回所有的管理账户集合
     * @return 以List方式返回
     */
    List<User> getUserList(@Param(value = "offset")Integer offset,@Param(value = "length")Integer length);
    /**
     * 最大页数
     * @return
     */
    int maxPage();
    /**
     * 删除指定账户
     * @param id 关键字
     * @return 删除了多少条记录
     */
    Integer deleteUser(Integer id);


    /**
     * 查找在数据库中和指定用户名重名的个数
     * @param username
     * @param id
     * @return 返回重名的个数，0表示不重名
     */
    Integer existsUser(@Param(value = "username")String username, @Param(value = "id")Integer id);

    /**
     * 查找在数据库中和指定用户名重名的个数
     * @param username
     * @return 返回重名的个数，0表示不重名
     */
    Integer existsUserUsername(@Param(value = "username")String username);
    /**
     * 注册
     * @param user
     * @return 添加了多少条记录
     */
    Integer saveUser(User user);
    /**
     * 根据标识符获取对应的管理账户对象
     * @param id
     * @return null表示没有找到
     */
    User getUser(int id);
}
