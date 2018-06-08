package com.homework.bbs.bbsdemo.service;

import com.homework.bbs.bbsdemo.dao.UserDao;
import com.homework.bbs.bbsdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    //自定义通过username查询对应的Password
//    public String queryPaswByName(String){}
}
