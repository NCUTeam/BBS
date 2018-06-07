package com.homework.bbs.bbsdemo.controller;

import com.homework.bbs.bbsdemo.entity.PostArticle;
import com.homework.bbs.bbsdemo.entity.ReplyArticle;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.service.ReplyArticleService;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
/*@RequestMapping(value = "/comments")*/
public class ReplyArticleController {
    @Autowired
    private ReplyArticleService replyArticleService;

    /*删除回帖及相关评论*/
    @DeleteMapping("/comments/user/{user_id}/pid/{pid}")
    @Transactional
    public Result<ReplyArticle> removeReplyArticle(@PathVariable("user_id") Integer userId,@PathVariable("pid") Integer pid, @RequestParam("id") Integer id){

        if(pid != 0){
            /*只是二级评论，删除当前这一条*/
         return replyArticleService.removeReplyArticle(id);
        }else {
            /*
            * 一级评论，需要删除回帖，以及二级评论
            * */

            //首先删除二级评论(pid==userId)
            replyArticleService.deleteByPid(userId);

            //删除pid=0的一级评论
            return replyArticleService.removeReplyArticle(id);
        }

    }

    /*查看当前文章所有回帖及评论*/
    @GetMapping("/comments")
    public Result<ReplyArticle> getReplyArticleByArticleId(@RequestParam("articleId") Integer articleId){

        return ResultUtil.success(replyArticleService.getReplyArticleByArticleId(articleId));
    }

    /*新增回帖或评论*/
    @Transactional
    @PostMapping("/comments")
    public Result<ReplyArticle> createReplyArticle(@Valid ReplyArticle replyArticle, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        replyArticleService.createReplyArticle(replyArticle);
        return ResultUtil.success("新增评论成功");
    }


}
