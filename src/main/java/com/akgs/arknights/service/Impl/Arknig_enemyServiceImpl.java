package com.akgs.arknights.service.Impl;

import com.akgs.arknights.dao.Arknig_enemyDao;
import com.akgs.arknights.model.Arknig_enemy;
import com.akgs.arknights.service.Arknig_enemyService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Primary
@Service
public class Arknig_enemyServiceImpl implements Arknig_enemyService {
    @Resource
    private Arknig_enemyDao arknig_enemyDao;

    /**
     * 查询敌方图鉴
     *
     * @return 敌方数据集
     */
    @Override
    public List<Arknig_enemy> getArknig_enemy_List() {

        return arknig_enemyDao.getArknig_enemy_List();
    }

    /**
     * 根据 根据 分组，攻击方式进行查询
     *
     * @param grouping
     * @param attackmode
     * @return
     */
    public List<Arknig_enemy> getArknig_enemy(String grouping, String attackmode) {
        return arknig_enemyDao.getArknig_enemy(grouping, attackmode);
    }

    /**
     * 删除敌方图鉴
     *
     * @param id 关键字
     * @return
     */
    public boolean deleteArknig_enemy(Integer id) {
        boolean status = false;//存储修改结果
        if (id != null) {
            int n = arknig_enemyDao.deleteArknig_enemy(id);
            if (n == 1) {
                status = true;
            }

        }
        return status;
    }

    /**
     * 修改敌方图鉴
     *
     * @param arknig_enemy id不能为空
     * @return
     */
    public boolean updateArknig_enemy(Arknig_enemy arknig_enemy) {
        boolean status = false;//存储修改结果
        if (arknig_enemyDao.existsArknig_enemy(arknig_enemy.getName()) == 0) {//如果不重名
            if (arknig_enemyDao.updateArknig_enemy(arknig_enemy) == 1) {
                status = true;
            } else {
                status = false;
            }
        }
        return status;
    }

    /**
     * 重命名
     *
     * @param name
     * @return
     */
    public boolean existsArknig_enemy(String name) {
        if (arknig_enemyDao.existsArknig_enemy(name) == 0){
            return false;
        }else{
            return true;
        }

}
    /**
     * 重命名
     * @param name
     * @return
     */
    public boolean existsname(String name) {
        if (arknig_enemyDao.existsname(name) == 0) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * 添加敌方图鉴
     * @param arknig_enemy
     * @return
     */
    public boolean saveArknig_enemy(Arknig_enemy arknig_enemy) {
        boolean judge = false;
        int i = arknig_enemyDao.saveArknig_enemy(arknig_enemy);
        if (i > 0) {
            judge = true;
        }
        return judge;
    }

    /**
     * 根据ID获取对应的敌方对象
     * @param id
     * @return
     */
    public Arknig_enemy togetArknig_enemy(Integer id){
        Arknig_enemy arknig_enemy=null;
        if(id!=null){
            arknig_enemy=arknig_enemyDao.togetArknig_enemy(id);
        }
        return arknig_enemy;
    }
}
