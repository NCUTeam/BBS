package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.dao.PostArticleDao;
import com.homespients.bbsbackstage.entity.PostArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchPostController {

    @Autowired
    private PostArticleDao postArticleDao;

    /**
     * 查询界面采用表单提交
     * 查询帖子，并将查询功能回显在当前的查询界面
     */
    @GetMapping(value = "/post/search")
    public String selectPost(@RequestParam("s") String content,
                             Model model){
        List<PostArticle> list = postArticleDao.findAll();
        //存放查询到的结果
        List<PostArticle> result = new ArrayList<>();
        for (PostArticle post:list) {
            if(post.getTitle().contains(content)||
                    post.getArticleContent().contains(content)){
                result.add(post);
            }
        }
        model.addAttribute("resultOfSearch", result);
        return "searchResult";
    }
}
