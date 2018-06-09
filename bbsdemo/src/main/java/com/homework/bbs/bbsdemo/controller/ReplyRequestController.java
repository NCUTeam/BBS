package com.homework.bbs.bbsdemo.controller;


import com.homework.bbs.bbsdemo.entity.ReplyRequest;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.service.ReplyRequestService;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ReplyRequestController {
    @Autowired
    private ReplyRequestService replyRequestService;

    /*删除回帖及相关评论*/
    @DeleteMapping("/request_comments/user/{user_id}/pid/{pid}")
    @Transactional
    public Result<ReplyRequest> removeReplyRequest(@PathVariable("user_id") Integer userId, @PathVariable("pid") Integer pid, @RequestParam("id") Integer id){

        if(pid != 0){
            /*只是二级评论，删除当前这一条*/
            return replyRequestService.removeReplyRequest(id);
        }else {
            /*
             * 一级评论，需要删除回帖，以及二级评论
             * */

            //首先删除二级评论(pid==userId)
            replyRequestService.deleteByPid(userId);

            //删除pid=0的一级评论
            return replyRequestService.removeReplyRequest(id);
        }

    }

    /*查看当前文章所有回帖及评论*/
    @GetMapping("/request_comments")
    public Result<ReplyRequest> getReplyRequestByArticleId(@RequestParam("articleId") Integer articleId){

        return ResultUtil.success(replyRequestService.getReplyRequestByArticleId(articleId));
    }

    /*新增回帖或评论*/
    @Transactional
    @PostMapping("/request_comments")
    public Result<ReplyRequest> createReplyRequest(@Valid ReplyRequest replyRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        replyRequestService.createReplyRequest(replyRequest);
        return ResultUtil.success("新增评论成功");
    }

}
