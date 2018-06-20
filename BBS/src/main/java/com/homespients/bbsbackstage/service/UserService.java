package com.homespients.bbsbackstage.service;

import com.homespients.bbsbackstage.dao.UserDao;
import com.homespients.bbsbackstage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 添加注册新用户的信息
     */
    public void addUser(User user){
        userDao.save(user);
    }

    /**
     * 通过名字查询用户信息
     */
    public List<User> queryByName(String username){
        return userDao.findByUsername(username);
    }

    /**
     *管理员查询当前的所有用户
     */
    public List<User> queryAllInfo(){
        return userDao.findAll();
    }

    /**
     * 修改用户信息
     */
    public void updateUser(User user){
        userDao.save(user);
    }

    /**
     * 管理员拥有删除普通用户的权限
     */
    public void deleteUser(BigInteger id){
        userDao.deleteById(id);
    }
//    //自定义通过username查询对应的Password
//    public String queryPaswByName(String){}
}
