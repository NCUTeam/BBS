package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.dao.PostArticleDao;
import com.homespients.bbsbackstage.dao.UserDao;
import com.homespients.bbsbackstage.entity.PostArticle;
import com.homespients.bbsbackstage.entity.User;
import com.homespients.bbsbackstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

@Controller
public class UpdateMemberController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostArticleDao postArticleDao;

    //修改用户的信息
    //前往修改页面，并进行回显
//    @GetMapping(value = "/user/info")
//    public String toUpdatePage(HttpSession session, Model model){
//        System.out.println((String)session.getAttribute("username"));
//        List<User> user = userService.queryByName((String)session.getAttribute("username"));
//        User user1 = user.get(0);
//        model.addAttribute("user", user1);
//        return "member";
//    }
    //修改页面进行修改
    @PutMapping(value = "/user/update")
    public String updatePage(User user, HttpSession session){
        User users = (User) session.getAttribute("users");
        user.setUser_id(users.getUser_id());
        user.setPassword(users.getPassword());
        user.setEmail(users.getEmail());
        user.setPhone(users.getPhone());
        userService.updateUser(user);
        return "member";
    }

    @GetMapping(value = "/member")
    public String findByAuthorId(Model model, HttpSession session){
        /*用户查看自己发布的所有帖子*/
        List<User> list1 = userDao.findByUsername((String) session.getAttribute("username"));
        String s = String.valueOf(list1.get(0).getUser_id());
        List<PostArticle> list = postArticleDao.findByAuthorId(Integer.parseInt(s));
        model.addAttribute("userArticle", list);
        /*查询用户的当前信息*/
        List<User> user = userService.queryByName((String)session.getAttribute("username"));
        User user1 = user.get(0);
        model.addAttribute("user", user1);
        return "member";
    }

}
