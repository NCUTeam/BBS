package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.dao.PostArticleDao;
import com.homespients.bbsbackstage.dao.UserDao;
import com.homespients.bbsbackstage.entity.PostArticle;
import com.homespients.bbsbackstage.entity.Result;
import com.homespients.bbsbackstage.entity.User;
import com.homespients.bbsbackstage.service.PostArticleService;
import com.homespients.bbsbackstage.service.ReplyArticleService;
import com.homespients.bbsbackstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PostArticleController {
    @Autowired
    private PostArticleService service;

    @Autowired
    private PostArticleDao postArticleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private ReplyArticleService replyArticleService;

    /** @Description 获取所有的文章列表 @Param @Return List<PostArticle> */
    @GetMapping(value = "/post_article")
    public String findAll(Model model) {
        List<PostArticle> list = postArticleDao.findAll();
        model.addAttribute("allPostArticle", list);
        return "index";
    }

    /*发帖*/
    @PostMapping(value = "/post_article")
    public String PostArticles(PostArticle postArticle, BindingResult bindingResult,
                               @RequestParam("checkbox") String category) {
//        if(bindingResult.hasErrors()){
//            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
//        }
        List<User> list1 = userDao.findByUsername((String) session.getAttribute("username"));
        Integer userId = Integer.parseInt(String.valueOf(list1.get(0).getUser_id()));
        postArticle.setAuthorId(userId);
        if(category == "qczj"){
            postArticle.setCategoryId(1);
        }else if(category.equals("ylxw")){
            postArticle.setCategoryId(2);
        }else if(category.equals("rcsh")){
            postArticle.setCategoryId(3);
        }else if(category.equals("xljk")){
            postArticle.setCategoryId(4);
        }else if(category.equals("kjdr")){
            postArticle.setCategoryId(5);
        }
        service.PostArticles(postArticle);
        return "index";
    }


    /*查看帖子详情*/
    @GetMapping(value = "/post_article/articleId/{articleId}")
    public Result<PostArticle> findByArticleId(@PathVariable("articleId") Integer articleId){
        return service.findByArticleId(articleId);
    }

    /*根据分类查询帖子列表*/
    @GetMapping(value = "/post_article/categoryId/{categoryId}")
    public String findByCategoryId(@PathVariable("categoryId") Integer categoryId,
                                   Model model){
        List<PostArticle> list = postArticleDao.findByCategoryId(categoryId);
        model.addAttribute("categoryPost", list);
        return "前往分类的界面";
    }

    /*删除帖子*/
    @DeleteMapping("/post_article")
    public String deletePostArticle(@RequestParam("articleId") Integer articleId){
        /*
         * 删除帖子以及相关回帖和评论
         * */
        //删除相关回帖和评论
        replyArticleService.deleteByArticleId(articleId);
        postArticleDao.deleteById(articleId);
        //service.deletePostArticle(articleId)
        //删除帖子
        return "redirect:/member";
    }

}