package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.entity.User;
import com.homespients.bbsbackstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AllUserInfoController {

    @Autowired
    private UserService userService;

    //@GetMapping()
    public String usersInfo(Model model){
        List<User> users = userService.queryAllInfo();
        model.addAttribute("users", users);

        return "返回用户界面";
    }
}
