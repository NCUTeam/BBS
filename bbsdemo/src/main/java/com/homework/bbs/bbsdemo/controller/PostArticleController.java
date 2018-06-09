package com.homework.bbs.bbsdemo.controller;

import com.homework.bbs.bbsdemo.entity.PostArticle;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.service.PostArticleService;
import com.homework.bbs.bbsdemo.service.ReplyArticleService;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PostArticleController {
    @Autowired
    private PostArticleService service;

    @Autowired
    private ReplyArticleService replyArticleService;

    /** @Description 获取所有的文章列表 @Param @Return List<PostArticle> */
    @GetMapping(value = "/post_article")
    public Result<PostArticle> findAll()
    {
        return service.findAll();
    }

    /*发帖*/
    @PostMapping(value = "/post_article")
    @Transactional
    public Result<PostArticle> PostArticles(@Valid PostArticle postArticle, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        service.PostArticles(postArticle);
       return ResultUtil.success("帖子发布成功");
    }


    /*查看帖子详情*/
    @GetMapping(value = "/post_article/articleId/{articleId}")
    public Result<PostArticle> findByArticleId(@PathVariable("articleId") Integer articleId){
        return service.findByArticleId(articleId);
    }


    /*用户查看自己发布的所有帖子*/
    @GetMapping(value = "/user/{user_id}/post_article")
    public Result<PostArticle> findByAuthorId(@PathVariable("user_id") int authorId){
      return service.findByAuthorId(authorId);
    }

    /*根据分类查询帖子列表*/
    @GetMapping(value = "/post_article/categoryId/{categoryId}")
    public Result<PostArticle> findByCategoryId(@PathVariable("categoryId") Integer categoryId){
        return service.findByCategoryId(categoryId);
    }
    /*删除帖子*/
    @DeleteMapping("/post_article")
    @Transactional
    public Object deletePostArticle(@RequestParam("articleId") Integer articleId){
        /*
        * 删除帖子以及相关回帖和评论
        * */


        //删除相关回帖和评论
        replyArticleService.deleteByArticleId(articleId);
        //删除帖子
        return service.deletePostArticle(articleId);


    }

}
