package com.akgs.arknights.dao;



import com.akgs.arknights.model.Arknig_role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Arknig_roleDao {
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
    /**
     * 根据标签ID查询角色
     * @return 以List方式返回
     */
    List<Arknig_role> getArknig_labelList(@Param(value = "label_id") String label_id);
    /**
     * 根据角色ID查询角色
     * @return 以List方式返回
     */

    Arknig_role getArknig_role(@Param(value = "role_id")Integer role_id);

    /**
     * 修改角色信息
     * @return
     */
    Integer updateArknig_role(Arknig_role arknig_role);
    /**
     * 查找在数据库中和指定角色重名的个数
     * @param name
     * @param role_id
     * @return 返回重名的个数，0表示不重名
     */
    Integer existsArknig_role(@Param(value = "name")String name, @Param(value = "role_id")Integer role_id);
    /**
     * 重名
     * @param name
     * @return 返回重名的个数，0表示不重名
     */
    Integer existsname(@Param(value = "name")String name);
    /**
     * 保存指定角色数据库
     * @param arknig_role
     * @return 添加了多少条记录
     */
    Integer saveArknig_role(Arknig_role arknig_role);

    /**
     * 删除指定角色
     * @param role_id 关键字
     * @return 删除了多少条记录
     */
    Integer deleteArknig_role(Integer role_id);
}
