package com.homework.bbs.bbsdemo.controller;

import com.homework.bbs.bbsdemo.entity.User;
import com.homework.bbs.bbsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 添加注册新用户的信息
     */
    @PostMapping(value = "/user/register")
    public void add(User user, Map<String, Object> map){
        if(user.getUsername() == null || user.getPassword() == null
                || user.getEmail() == null){
            map.put("msg", "不能为空，请重新填写");
            System.out.println("信息不能为空，请重新填写");
        }else {
            userService.addUser(user);
        }
    }
}
