package com.homespients.bbsbackstage.controller;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.homespients.bbsbackstage.entity.User;
import com.homespients.bbsbackstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 添加注册新用户的信息
     */
    @PostMapping(value = "/user/register")
    public String add(@RequestParam("sure_password") String sure_password,
                      User user, Map<String, Object> map, HttpSession session){
        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())
                || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPhone())){
            map.put("msg1", "其他填充区域不能为空，请重新填写");
            return "userlogin";
        }else if (!user.getPassword().equals(sure_password)){
            map.put("msg1", "两次输入的密码不正确，请重新输入");
            return "userlogin";
        }else{
            session.setAttribute("username", user.getUsername());
            session.setAttribute("users", user);
            user.setAdmin("false");
            userService.addUser(user);
            return "redirect:/index";
        }
    }
    /**
     * 管理员注册
     */
    @PostMapping(value = "/admin/register")
    public String addAdmin(@RequestParam("sure_password") String sure_password,
                      User user, Map<String, Object> map, HttpSession session){
        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())
                || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPhone())){
            map.put("msg1", "其他填充区域不能为空，请重新填写");
            return "userlogin";
        }else if (!user.getPassword().equals(sure_password)){
            map.put("msg1", "两次输入的密码不正确，请重新输入");
            return "userlogin";
        }else{
            session.setAttribute("username", user.getUsername());
            user.setAdmin("true");
            session.setAttribute("users", user);
            userService.addUser(user);
            return "redirect:/mindex";
        }
    }
}
