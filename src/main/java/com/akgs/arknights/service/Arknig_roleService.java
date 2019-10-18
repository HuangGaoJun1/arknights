package com.akgs.arknights.service;

import com.akgs.arknights.model.Arknig_role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Arknig_roleService {
    /**
     * 根据职业ID查询角色
     * @param opn_id
     * @return 以List方式返回
     */
    List<Arknig_role> getArknig_opnList(@Param(value = "opn_id") Integer opn_id);
    /**
     * 返回所有的角色集合
     * @return 以List方式返回
     */
    List<Arknig_role> getArknig_roleList(Arknig_role arknig_role);

    Arknig_role getArknig_role(Integer role_id);
    /**
     * 修改账户的基本信息
     *
     * @param arknig_role role_id不能为空
     * @return 更改了多少条记录
     */
    boolean updateArknig_role(Arknig_role arknig_role);
    /**
     * 删除角色的头像文件，并将数据库对应的头像信息清空
     * 说明：无论该角色是否真的存在头像文件，都会一并删除不会出BUG
     * @param roleid  角色的主键
     * @param basePath 项目根目录网址，用于删除角色对应的头像文件
     */
    void removeRoleProfilePicture(Integer roleid,String basePath);
    /**
     * 删除指定角色
     * @param role_id 关键字
     * @return 删除了多少条记录
     */
    boolean deleteArknig_role(Integer role_id);
    /**
     * 查找在数据库中和指定角色重名的个数
     * @param name
     * @param role_id
     * @return 返回重名的个数，0表示不重名
     */
    boolean existsArknig_role(@Param(value = "name")String name, @Param(value = "role_id")Integer role_id);
    /**
     * 重名
     * @param name
     * @return 返回重名的个数，0表示不重名
     */
    boolean existsname(@Param(value = "name")String name);
    /**
     * 保存指定角色数据库
     * @param arknig_role
     * @return 添加了多少条记录
     */
    boolean saveArknig_role(Arknig_role arknig_role);
}
