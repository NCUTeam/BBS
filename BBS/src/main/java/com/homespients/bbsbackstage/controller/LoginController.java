package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.entity.User;
import com.homespients.bbsbackstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String login(@RequestParam("id") String username,
                      @RequestParam("passwd") String password,
                      Map<String, Object> map,
                      HttpSession session){
        List<User> list = userService.queryByName(username);
        if(username==null || password==null){
            //提醒用户输入完整登陆信息
            map.put("msg", "提醒用户输入完整登陆信息");
            return "userlogin";
        }else if(list.size()==0){
            //提醒登陆出错的信息原因
            map.put("msg", "用户不存在，请重新输入");
            //返回登陆界面
            return "userlogin";
        }else if(!list.get(0).getPassword().equals(password)){
            //提醒用户输入的密码不正确
            map.put("msg", "提醒用户输入的密码不正确");
            return "userlogin";
        }else if(list.get(0).getAdmin().equals("false")){
            session.setAttribute("username",username);
            return "redirect:/index";
        }else{
            session.setAttribute("username",username);
            return "redirect:/mindex";
        }
    }

    //前往登陆界面
    @GetMapping(value = "/userlogin")
    public String toLoginPage(){
        return "userlogin";
    }
}
