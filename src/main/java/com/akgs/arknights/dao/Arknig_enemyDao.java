package com.akgs.arknights.dao;
import com.akgs.arknights.model.Arknig_enemy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Arknig_enemyDao {
    /**
     * 返回所有的敌方资料集合
     * @return 以List方式返回
     */
    List<Arknig_enemy> getArknig_enemy_List();


    /**
     * 根据 分组，攻击方式进行查询
     * @param grouping
     * @param attackmode
     * @return
     */
    List<Arknig_enemy> getArknig_enemy(@Param(value = "grouping")String grouping,@Param(value = "attackmode")String attackmode);
    /**
     * 删除指定账户
     * @param id 关键字
     * @return 删除了多少条记录
     */
    Integer deleteArknig_enemy(Integer id);

    /**
     * 修改账户的基本信息
     * @param arknig_enemy id不能为空
     * @return 更改了多少条记录
     */
    Integer updateArknig_enemy(Arknig_enemy arknig_enemy);

    /**
     * 查找在数据库中和指定用户名重名的个数
     * @param name
     * @return 返回重名的个数，0表示不重名
     */
    Integer existsArknig_enemy(@Param(value = "name")String name);

    /**
     * 查找在数据库中和指定用户名重名的个数
     * @param name
     * @return 返回重名的个数，0表示不重名
     */
    Integer existsname(@Param(value = "name")String name);
    /**
     * 添加敌方图鉴
     * @param arknig_enemy
     * @return 添加了多少条记录
     */
    Integer saveArknig_enemy(Arknig_enemy arknig_enemy);
    /**
     * 根据标识符获取对应的管理账户对象
     * @param id
     * @return null表示没有找到
     */
    Arknig_enemy togetArknig_enemy(int id);
}
