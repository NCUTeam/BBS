package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.dao.RequestArticleDao;
import com.homespients.bbsbackstage.dao.UserDao;
import com.homespients.bbsbackstage.entity.PostArticle;
import com.homespients.bbsbackstage.entity.RequestArticle;
import com.homespients.bbsbackstage.entity.Result;
import com.homespients.bbsbackstage.entity.User;
import com.homespients.bbsbackstage.service.ReplyRequestService;
import com.homespients.bbsbackstage.service.RequestArticleService;
import com.homespients.bbsbackstage.service.UserService;
import com.homespients.bbsbackstage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class RequestArticleController {
    @Autowired
    private RequestArticleService service;

    @Autowired
    private RequestArticleDao requestArticleDao;

    @Autowired
    private ReplyRequestService replyRequestService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    /** @Description 获取所有的文章列表 @Param @Return List<PostArticle> */
    @GetMapping(value = "/request_article")
    public String findAll() {
        List<RequestArticle> list = requestArticleDao.findAll();
        return "xqgc";
    }

    /*发帖*/
    @PostMapping(value = "/request_article")
    public Result<RequestArticle> RequestArticles(@Valid RequestArticle requestArticle, BindingResult bindingResult,
                                                  @RequestParam("checkbox") String category) {
//        if(bindingResult.hasErrors()){
//            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
//        }
        List<User> list1 = userDao.findByUsername((String) session.getAttribute("username"));
        Integer userId = Integer.parseInt(String.valueOf(list1.get(0).getUser_id()));
        requestArticle.setAuthorId(userId);
        if(category == "qczj"){
            requestArticle.setCategoryId(1);
        }else if(category.equals("ylxw")){
            requestArticle.setCategoryId(2);
        }else if(category.equals("rcsh")){
            requestArticle.setCategoryId(3);
        }else if(category.equals("xljk")){
            requestArticle.setCategoryId(4);
        }else if(category.equals("kjdr")){
            requestArticle.setCategoryId(5);
        }
        service.RequestArticles(requestArticle);
        return ResultUtil.success("帖子发布成功");
    }


//    /*查看帖子详情*/
//    @GetMapping(value = "/request_article/requestId/{requestId}")
//    public Result<RequestArticle> findByRequestId(@PathVariable("requestId") Integer requestId){
//        return service.findByRequestId(requestId);
//    }


    /*用户查看自己发布的所有帖子*/
    @GetMapping(value = "/user/{user_id}/request_article")
    public Result<RequestArticle> findByAuthorId(@PathVariable("user_id") int authorId){
        return service.findByAuthorId(authorId);
    }

    /*根据分类查询帖子列表*/
    @GetMapping(value = "/request_article/categoryId/{categoryId}")
    public String findByCategoryId(@PathVariable("categoryId") Integer categoryId,
                                                   Model model){
        List<RequestArticle> list = requestArticleDao.findByCategoryId(categoryId);
        model.addAttribute("categoryPost", list);
        return "前往分类的界面";
    }

    /*删除帖子*/
    @DeleteMapping("/request_article")
    public String deleterRquestArticle(@RequestParam("requestId") Integer requestId){
        /*
         * 删除帖子以及相关回帖和评论
         * */
        //删除相关回帖和评论
        replyRequestService.deleteByArticleId(requestId);
        service.deleteRequestArticle(requestId);
        //删除帖子
        //这个member需重定向修改
        return "redirect:/member";


    }


}
