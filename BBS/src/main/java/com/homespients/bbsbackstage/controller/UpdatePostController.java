package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.dao.PostArticleDao;
import com.homespients.bbsbackstage.entity.PostArticle;
import com.homespients.bbsbackstage.service.PostArticleService;
import com.homespients.bbsbackstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UpdatePostController {

    @Autowired
    private PostArticleService service;

    @Autowired
    private PostArticleDao dao;

    //前往帖子修改页面
    @GetMapping(value = "/post/update/{id}")
    public String toUpdatePostPage(@PathVariable("id") Integer id,
                                   Model model, HttpSession session){
        session.setAttribute("articleId", id);
        List<PostArticle> result = dao.findByArticleId(id);
        PostArticle postArticle = result.get(0);
        model.addAttribute("postInfo", postArticle);
        return "changeteizi";
    }

    //对帖子进行修改
    @PutMapping(value = "/post/update")
    public String updatePostPage(PostArticle postArticle, HttpSession session){
        Integer  post1 = (Integer) session.getAttribute("articleId");
        List<PostArticle> post2 = dao.findByAuthorId(post1);
        PostArticle post = post2.get(0);
        postArticle.setArticleId(post.getArticleId());
        postArticle.setAuthorId(post.getAuthorId());
        postArticle.setCreateTime(post.getCreateTime());
        postArticle.setCategoryId(post.getCategoryId());
        dao.save(postArticle);
        return "redirect:/post/update/"+session.getAttribute("articleId");
    }

}
