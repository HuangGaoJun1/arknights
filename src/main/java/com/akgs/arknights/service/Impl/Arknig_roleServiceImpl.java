package com.akgs.arknights.service.Impl;

import com.akgs.arknights.dao.Arknig_roleDao;
import com.akgs.arknights.model.Arknig_opn;
import com.akgs.arknights.model.Arknig_role;
import com.akgs.arknights.service.Arknig_roleService;
import com.akgs.arknights.util.file.MyFileOperator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
@Primary
@Service
public class Arknig_roleServiceImpl implements Arknig_roleService {
    @Resource
    private Arknig_roleDao arknig_roleDao;

    /**
     * 根据职业ID查询角色
     * @param opn_id
     * @return 以List方式返回
     */
    @Override
    public List<Arknig_role> getArknig_opnList(Integer opn_id) {
        return arknig_roleDao.getArknig_opnList(opn_id);
    }

    /**
     * 返回所有的角色集合
     * @return 以List方式返回
     */
    public List<Arknig_role> getArknig_roleList(Arknig_role arknig_role) {
        return arknig_roleDao.getArknig_roleList(arknig_role);
    }


    public Arknig_role getArknig_role(Integer role_id) {

            Arknig_role arknig_role=null;
            if(role_id!=null){
                arknig_role=arknig_roleDao.getArknig_role(role_id);
            }
            return arknig_role;

    }


    public boolean updateArknig_role(Arknig_role arknig_role) {
        boolean status = false;//存储修改结果
        if (arknig_roleDao.existsArknig_role(arknig_role.getName(), arknig_role.getRole_id()) == 0) {//如果不重名
            if (arknig_roleDao.updateArknig_role(arknig_role) == 1) {
                status = true;
            } else {
                status = false;
            }
        }

        return status;
    }

    public void removeRoleProfilePicture(Integer role_id, String basePath) {
            //删除账户对应的图片
            Arknig_role arknig_role=arknig_roleDao.getArknig_role(role_id);//读取相应的记录
            String picUrl=arknig_role.getRole_imges();//获取头像地址
            if(!StringUtils.isEmpty(picUrl)){//如果头像存在
                arknig_role.setRole_imges("");//清空图片地址
                arknig_roleDao.updateArknig_role(arknig_role);
                MyFileOperator.deleteFile(basePath+ Arknig_role.ROLE_PROFILE_PICTURE_UPLOAD_URL+picUrl);//删除图片
            }
        }


    public boolean deleteArknig_role(Integer role_id) {
        boolean status = false;//存储修改结果
        if (role_id != null ) {
            int n=arknig_roleDao.deleteArknig_role(role_id);
            if (n==1) {
                status = true;
            }

        }
        return status;
    }


    public boolean existsArknig_role(String name, Integer role_id) {
        if (arknig_roleDao.existsArknig_role(name,role_id)==0){
        return false;
    }else{
           return  true;
        }
    }


    public boolean existsname(String name) {
        if (arknig_roleDao.existsname(name)==0){
            return false;
        }else{
            return  true;
        }
    }


    public boolean saveArknig_role(Arknig_role arknig_role) {
        boolean judge = false;
        int i = arknig_roleDao.saveArknig_role(arknig_role);
        if (i > 0) {
            judge = true;
        }
        return judge;
    }

}
