package com.akgs.arknights.service.Impl;

import com.akgs.arknights.dao.UserDao;
import com.akgs.arknights.model.User;
import com.akgs.arknights.service.UserService;
import com.akgs.arknights.util.SHA;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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






    public List<User> getUserList() {
        return userDao.getUserList();
   }

    @Override
    public boolean deleteUser(Integer id,Integer userId) {
        boolean status = false;//存储修改结果
        if (id != null && userId != null) {
            if (userId != id.intValue()) {//如果不是自己删除自己
                int n=userDao.deleteUser(id);
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

    /**
     * 重命名
     *
     * @param username
     * @param id
     * @return
     */

    public boolean existsUser(String username, Integer id) {
        return false;
    }

    public boolean existsUserUsername(String username) {
        if (userDao.existsUserUsername(username) == 0) {
            return false;
        } else {
            return true;
        }
    }



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





    public User getUser(Integer id){
        User user=null;
        if(id!=null){
            user=userDao.getUser(id);
        }
        return user;
    }

}