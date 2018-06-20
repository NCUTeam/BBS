package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.dao.PostArticleDao;
import com.homespients.bbsbackstage.dao.ReplyArticleDao;
import com.homespients.bbsbackstage.dao.ReplyRequestDao;
import com.homespients.bbsbackstage.dao.RequestArticleDao;
import com.homespients.bbsbackstage.entity.*;
import com.homespients.bbsbackstage.service.ReplyRequestService;
import com.homespients.bbsbackstage.service.UserService;
import com.homespients.bbsbackstage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ReplyRequestController {
    @Autowired
    private ReplyRequestService replyRequestService;

    @Autowired
    private PostArticleDao postArticleDao;

    @Autowired
    private ReplyArticleDao replyArticleDao;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestArticleDao requestArticleDao;

    @Autowired
    private ReplyRequestDao replyRequestDao;

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

//    /*查看当前文章所有回帖及评论*/
//    @GetMapping("/request_comments")
//    public Result<ReplyRequest> getReplyRequestByArticleId(@RequestParam("articleId") Integer articleId){
//
//        return ResultUtil.success(replyRequestService.getReplyRequestByArticleId(articleId));
//    }

    /*新增回帖或评论*/
    @PostMapping("/request_comments")
    public String createReplyRequest(ReplyRequest replyRequest, BindingResult bindingResult,
                                                   @RequestParam("article") String article,
                                                   @RequestParam("author") String author,
                                                   HttpSession session){
//        if(bindingResult.hasErrors()){
//            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
//        }
        replyRequest.setArticleId(Integer.parseInt(article));
        replyRequest.setReplyUserId(Integer.parseInt(author));
        String str = (String) session.getAttribute("username");
        List<User> list = userService.queryByName(str);
        replyRequest.setUserId(Integer.parseInt(String.valueOf(list.get(0).getUser_id())));
        ResultUtil.success("新增评论成功");
        replyRequestService.createReplyRequest(replyRequest);
        return "redirect:/xqtieziDetail/comments/"+article;
    }

    /**
     * 前往帖子详情界面，并显示当前的界面,查看当前文章所有回帖及评论
     */
    @GetMapping(value = "/xqtieziDetail/comments/{article_id}")
    public String toDetail(@PathVariable("article_id") String articleId, Model model){
        List<RequestArticle> list = requestArticleDao.findByArticleId(Integer.parseInt(articleId));
        model.addAttribute("articleDetail", list);
        List<ReplyRequest> list1 = replyRequestDao.findByArticleId(Integer.parseInt(articleId));
        model.addAttribute("articleComments", list1);
        return "tieziDetail";
    }

}
