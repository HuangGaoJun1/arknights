package com.akgs.arknights.dao;
import com.akgs.arknights.model.Admin;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;


import java.util.List;


public interface AdminDao {
   /**
          l  * 根据账户名和密码去数据库查询
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
    Integer updateAdmin(Admin admin);
    /**
     * 返回所有的管理账户集合
     * @return 以List方式返回
     */
    List<Admin>getAdminList(@Param(value = "offset")Integer offset,@Param(value = "length")Integer length);
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
    Integer deleteAdmin(Integer id);


 /**
  * 查找在数据库中和指定用户名重名的个数
  * @param username
  * @param id
  * @return 返回重名的个数，0表示不重名
  */
 Integer existsAdmin(@Param(value = "username")String username, @Param(value = "id")Integer id);

    /**
     * 查找在数据库中和指定用户名重名的个数
     * @param username
     * @return 返回重名的个数，0表示不重名
     */
    Integer existsUsername(@Param(value = "username")String username);
    /**
     * 保存指定账户金数据库
     * @param admin
     * @return 添加了多少条记录
     */
    Integer saveAdmin(Admin admin);
    /**
     * 根据标识符获取对应的管理账户对象
     * @param id
     * @return null表示没有找到
     */
    Admin getAdmin(int id);
}
