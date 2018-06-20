package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.dao.PostArticleDao;
import com.homespients.bbsbackstage.dao.ReplyArticleDao;
import com.homespients.bbsbackstage.entity.PostArticle;
import com.homespients.bbsbackstage.entity.ReplyArticle;
import com.homespients.bbsbackstage.entity.Result;
import com.homespients.bbsbackstage.entity.User;
import com.homespients.bbsbackstage.service.ReplyArticleService;
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
public class ReplyArticleController {
    @Autowired
    private ReplyArticleService replyArticleService;

    @Autowired
    private PostArticleDao postArticleDao;

    @Autowired
    private ReplyArticleDao replyArticleDao;

    @Autowired
    private UserService userService;

    /*删除回帖及相关评论*/
    @DeleteMapping("/comments/user/{user_id}/pid/{pid}")
    public String removeReplyArticle(@PathVariable("user_id") Integer userId, @PathVariable("pid") Integer pid, @RequestParam("id") Integer id){

        if(pid != 0){
            /*只是二级评论，删除当前这一条*/
            replyArticleService.removeReplyArticle(id);
            return "redirect:/member";
        }else {
            /*
             * 一级评论，需要删除回帖，以及二级评论
             * */

            //首先删除二级评论(pid==userId)
            replyArticleService.deleteByPid(userId);

            //删除pid=0的一级评论
            replyArticleService.removeReplyArticle(id);
            return "redirect:/member";
        }

    }

//    /*查看当前文章所有回帖及评论*/
//    @GetMapping("/comments")
//    public Result<ReplyArticle> getReplyArticleByArticleId(@RequestParam("articleId") Integer articleId){
//
//        return ResultUtil.success(replyArticleService.getReplyArticleByArticleId(articleId));
//    }

    /*新增回帖或评论*/
    @PostMapping("/comments")
    public String createReplyArticle(@Valid ReplyArticle replyArticle, BindingResult bindingResult,
                                     @RequestParam("article") String article,
                                     @RequestParam("author") String author,
                                     HttpSession session){
//        if(bindingResult.hasErrors()){
//            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
//        }
        replyArticle.setArticleId(Integer.parseInt(article));
        replyArticle.setReplyUserId(Integer.parseInt(author));
        String str = (String) session.getAttribute("username");
        List<User> list = userService.queryByName(str);
        replyArticle.setUserId(Integer.parseInt(String.valueOf(list.get(0).getUser_id())));
        replyArticleService.createReplyArticle(replyArticle);
        ResultUtil.success("新增评论成功");
        return "redirect:/httieziDetail/comments/"+article;
    }


    /**
     * 前往帖子详情界面，并显示当前的界面,查看当前文章所有回帖及评论
     */
    @GetMapping(value = "/httieziDetail/comments/{article_id}")
    public String toDetail(@PathVariable("article_id") String articleId, Model model){
        List<PostArticle> list = postArticleDao.findByArticleId(Integer.parseInt(articleId));
        model.addAttribute("articleDetail", list);
        List<ReplyArticle> list1 = replyArticleDao.findByArticleId(Integer.parseInt(articleId));
        model.addAttribute("articleComments", list1);
        return "tieziDetail";
    }
}
