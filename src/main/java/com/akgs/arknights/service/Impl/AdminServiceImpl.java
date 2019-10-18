package com.akgs.arknights.service.Impl;

import com.akgs.arknights.dao.AdminDao;
import com.akgs.arknights.model.Admin;
import com.akgs.arknights.service.AdminService;
import com.akgs.arknights.util.SHA;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Primary
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

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    public boolean saveAdmin(Admin admin) {
        boolean judge = false;
        admin.setPassword(SHA.getResult("123456"));//默认密码
        int i = adminDao.saveAdmin(admin);
        if (i > 0) {
            judge = true;
        }
        return judge;
    }

    /**
     * 根据ID符获取对应的管理账户对象
     * @param id
     * @return null表示没有找到
     */
    public Admin getAdmin(Integer id){
        Admin admin=null;
        if(id!=null){
            admin=adminDao.getAdmin(id);
        }
        return admin;
    }
    /**
     * 查询管理员
     * @return 管理员数据集
     */
    public List<Admin>getAdminList(Integer page,Integer limit){
        int pagesize=10;
        /*if(page==null){
            page=1;
        }else if (page==0){
            page=1;
        }else if (page<1){
            page=1;
        }*/
        int offset=(page-1)*pagesize+1;
        return adminDao.getAdminList(offset-1,pagesize);
    }
    /**
     * 查询全部记录数，最大页
     * @return
     * @param limit
     */

    public int maxPage(Integer limit) {
        int maxPage;
        int pagesize=10;
        int total=adminDao.maxPage();
        if (total%pagesize==0){
            maxPage=total/pagesize;
        }else{
            maxPage=total/pagesize+1;
        }
        return maxPage;
    }
}