package com.homework.bbs.bbsdemo.controller;

import com.homework.bbs.bbsdemo.entity.RequestArticle;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.service.ReplyRequestService;
import com.homework.bbs.bbsdemo.service.RequestArticleService;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class RequestArticleController {
    @Autowired
    private RequestArticleService service;
    @Autowired
    private ReplyRequestService replyRequestService;

    /** @Description 获取所有的文章列表 @Param @Return List<PostArticle> */
    @GetMapping(value = "/request_article")
    public Result<RequestArticle> findAll()
    {
        return service.findAll();
    }

    /*发帖*/
    @PostMapping(value = "/request_article")
    @Transactional
    public Result<RequestArticle> RequestArticles(@Valid RequestArticle requestArticle, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        service.RequestArticles(requestArticle);
        return ResultUtil.success("帖子发布成功");
    }


    /*查看帖子详情*/
    @GetMapping(value = "/request_article/requestId/{requestId}")
    public Result<RequestArticle> findByRequestId(@PathVariable("requestId") Integer requestId){
        return service.findByRequestId(requestId);
    }


    /*用户查看自己发布的所有帖子*/
    @GetMapping(value = "/user/{user_id}/request_article")
    public Result<RequestArticle> findByAuthorId(@PathVariable("user_id") int authorId){
        return service.findByAuthorId(authorId);
    }

    /*根据分类查询帖子列表*/
    @GetMapping(value = "/request_article/categoryId/{categoryId}")
    public Result<RequestArticle> findByCategoryId(@PathVariable("categoryId") Integer categoryId){
        return service.findByCategoryId(categoryId);
    }
    /*删除帖子*/
    @DeleteMapping("/request_article")
    @Transactional
    public Object deleterRquestArticle(@RequestParam("requestId") Integer requestId){
        /*
         * 删除帖子以及相关回帖和评论
         * */


        //删除相关回帖和评论
        replyRequestService.deleteByArticleId(requestId);
        //删除帖子
        return service.deleteRequestArticle(requestId);


    }


}
