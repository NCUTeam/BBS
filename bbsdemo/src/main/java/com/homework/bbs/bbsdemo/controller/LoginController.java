package com.homework.bbs.bbsdemo.controller;

import com.homework.bbs.bbsdemo.entity.User;
import com.homework.bbs.bbsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    //验证用户登录是否正确
    @PostMapping(value = "/user/login")
    public void login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      Map<String, Object> map,
                      HttpSession session){
//        session.setAttribute("username",username);
//        session.setAttribute("password", password);
        List<User> list = userService.queryByName(username);
        System.out.println("list中的信息为"+list);
        if(username==null || password==null){
            //提醒用户输入完整登陆信息
            map.put("mag", "提醒用户输入完整登陆信息");
        }else if(list.size()==0){
            //提醒登陆出错的信息原因
            //request.setAttribute("msg", "用户不存在，请重新输入");
            map.put("mag", "用户不存在，请重新输入");
            //返回登陆界面
            //request.getRequestDispatcher("/login.html").forward(request, response);
        }else if(!list.get(0).getPassword().equals(password)){
            //提醒用户输入的密码不正确
            map.put("mag", "提醒用户输入的密码不正确");
        }else {
            System.out.println("登陆成功！！");
        }
    }
}
