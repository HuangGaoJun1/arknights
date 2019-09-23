package com.akgs.arknights.service.Impl;

import com.akgs.arknights.dao.AdminDao;
import com.akgs.arknights.model.Admin;
import com.akgs.arknights.service.AdminService;
import com.akgs.arknights.util.SHA;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
@Resource
    private AdminDao adminDao;

    /**
     * 登录
     *
     * @param username 账户名
     * @param password 密码
     * @return
     */
    @Override
    public Admin login(String username, String password) {
        if (password.length() != 32) {
            //将密码加密后再进行比对
            password = SHA.getResult(password);
        }
        Admin admin = adminDao.login(username, password);
        return admin;
    }

    /**
     * 删除
     *
     * @param id      关键字
     * @param adminId
     * @return
     */

    public boolean deleteAdmin(Integer id, Integer adminId) {
        boolean status = false;//存储修改结果
        if (id != null && adminId != null) {
            if (adminId != id.intValue()) {//如果不是自己删除自己
                 int n=adminDao.deleteAdmin(id);
                 if (n==1) {
                     status = true;
                 }
            }
        }
        return status;
    }

    public List<Admin> getAdminList() {
        return adminDao.getAdminList();
   }

    /**
     * 修改信息
     *
     * @param admin id不能为空
     * @return
     */

    public boolean updateAdmin(Admin admin) {
        boolean status = false;//存储修改结果
        if (adminDao.existsAdmin(admin.getUsername(), admin.getId()) == 0) {//如果不重名
            if (adminDao.updateAdmin(admin) == 1) {
                status = true;
            } else {
                status = false;
            }
        }
        return status;
    }

    /**
     * 修改密码
     */
    public boolean updatePassword(String newPass, Integer id) {
        Boolean status = false;//默认编辑失败
        newPass = SHA.getResult(newPass);
        Admin admin = new Admin();
        admin.setId(id);
        admin.setPassword(newPass);
        if (adminDao.updateAdmin(admin) > 0) {
            status = true;
        }
        return status;
    }

    /**
     * 重命名
     *
     * @param username
     * @param id
     * @return
     */

    public boolean existsAdmin(String username, Integer id) {
        return false;
    }

    public boolean existsUsername(String username) {
        if (adminDao.existsUsername(username) == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean saveAdmin(Admin admin) {
        boolean judge = false;
        admin.setPassword(SHA.getResult("123456"));//默认密码
        int i = adminDao.saveAdmin(admin);
        if (i > 0) {
            judge = true;
        }
        return judge;
    }

    public Admin getAdmin(Integer id){
        Admin admin=null;
        if(id!=null){
            admin=adminDao.getAdmin(id);
        }
        return admin;
    }

}