package com.akgs.arknights.service;
import com.akgs.arknights.model.Arknig_enemy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Arknig_enemyService {
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
    List<Arknig_enemy> getArknig_enemy(@Param(value = "grouping") String grouping,@Param(value = "attackmode")String attackmode);
    /**
     * 删除指定账户
     * @param id 关键字
     * @return 删除了多少条记录
     */
    boolean deleteArknig_enemy(Integer id);
    /**
     * 修改账户的基本信息
     * @param arknig_enemy id不能为空
     * @return 更改了多少条记录
     */
    boolean updateArknig_enemy(Arknig_enemy arknig_enemy);
    /**
     * 查找在数据库中和指定敌方名称重名的个数
     * @param name
     * @return true表示存在重名账户，false表示不存在
     */
    boolean existsArknig_enemy(@Param(value = "name")String name);

    /**
     * 判断敌方名称是否存在（用于创建新敌方图鉴的时候）
     * @param name
     * @return true表示存在，false表示存在
     */
    boolean existsname(String name);
    /**
     * 将账户信息存入数据库
     * @param arknig_enemy
     * @return true表示保存成功，false表示保存失败
     */
    boolean saveArknig_enemy(Arknig_enemy arknig_enemy);
    /**
     * 根据标识符获取对应的管理账户对象
     * @param id
     * @return null表示没有找到
     */
    Arknig_enemy togetArknig_enemy(Integer id);
}
