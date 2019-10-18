package com.akgs.arknights.service.Impl;

import com.akgs.arknights.dao.UserDao;
import com.akgs.arknights.model.User;
import com.akgs.arknights.service.UserService;
import com.akgs.arknights.util.SHA;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Primary
@Service
public class UserServiceImpl implements UserService {
@Resource
    private UserDao userDao;

    /**
     * 登录
     *
     * @param username 账户名
     * @param password 密码
     * @return
     */
    @Override
    public User userlogin(String username, String password) {
        if (password.length() != 32) {
            //将密码加密后再进行比对
            password = SHA.getResult(password);
        }
        User user = userDao.userlogin(username, password);
        return user;
    }







    /**
     * 查询管理员
     * @return 管理员数据集
     */
    public List<User>getUserList(Integer limit, Integer page){
        int pagesize=10;
        /*if(page==null){
            page=1;
        }else if (page==0){
            page=1;
        }else if (page<1){
            page=1;
        }*/
        int offset=(page-1)*pagesize+1;
        return userDao.getUserList(offset-1,pagesize);
    }
    /**
     * 查询全部记录数，最大页
     * @return
     */
    public int maxPage(Integer limit) {
        int maxPage;
        int pagesize=10;
        int total=userDao.maxPage();
        if (total%pagesize==0){
            maxPage=total/pagesize;
        }else{
            maxPage=total/pagesize+1;
        }
        return maxPage;
    }

    public boolean deleteUser(Integer id) {
        boolean status = false;//存储修改结果
        if (id != null ) {
                int n=userDao.deleteUser(id);
                if (n==1) {
                    status = true;
                }

        }
        return status;
    }



    /**
     * 修改信息
     *
     * @param user id不能为空
     * @return
     */

    public boolean updateUser(User user) {
        boolean status = false;//存储修改结果
        if (userDao.existsUser(user.getUsername(), user.getId()) == 0) {//如果不重名
            if (userDao.updateUser(user) == 1) {
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
        boolean status = false;//默认编辑失败
        newPass = SHA.getResult(newPass);
        User user = new User();
        user.setId(id);
        user.setPassword(newPass);
        if (userDao.updateUser(user) > 0) {
            status = true;
        }
        return status;
    }


    public boolean newPass(String newPass) {
        boolean status = false;//默认确认失败
        newPass = SHA.getResult(newPass);
        User user = new User();
        user.setPassword(newPass);
        if (userDao.saveUser(user) > 0) {
            status = true;
        }
        return status;
    }


    /**
     * 重命名
     *
     * @param username
     * @param
     * @return
     */

    public boolean existsUser(String username,Integer id) {
        if (userDao.existsUser(username,id) == 0) {
            return false;
        } else {
            return true;
        }

    }

    public boolean existsUserUsername(String username) {
        if (userDao.existsUserUsername(username) == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 添加用户
     * @param user
     * @return
     */

    @Override
    public boolean saveUser(User user) {
        boolean judge = false;
        user.setPassword(SHA.getResult("123456"));//默认密码
        int i = userDao.saveUser(user);
        if (i > 0) {
            judge = true;
        }
        return judge;
    }


    /**
     * 根据ID获取对应的用户
     * @param id
     * @return
     */

    public User getUser(Integer id){
        User user=null;
        if(id!=null){
            user=userDao.getUser(id);
        }
        return user;
    }

}