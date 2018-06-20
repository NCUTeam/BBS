package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class DeleteController {

    @Autowired
    private UserService userService;

    /**
     * 管理员可以删除用户
     */
    //@DeleteMapping()
    public String deleteUser(BigInteger id){
        userService.deleteUser(id);
        return "返回用户列表";
    }
}
