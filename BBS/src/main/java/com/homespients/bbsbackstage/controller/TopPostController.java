package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.dao.PostArticleDao;
import com.homespients.bbsbackstage.entity.PostArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopPostController {

    @Autowired
    private PostArticleDao postArticleDao;

    /**
     * 管理员点击帖子，将帖子置顶
     */
    @GetMapping(value = "/post/addtop/{id}")
    public String addTopPost(@PathVariable("id") Integer id){
        postArticleDao.updateTopPost("true", id);
        return "mindex";
    }
    /**
     * 管理员点击帖子，取消帖子置顶
     */
    @GetMapping(value = "/post/deletetop/{id}")
    public String deleteTopPost(@PathVariable("id") Integer id){
        postArticleDao.updateTopPost("false", id);
        return "mindex";
    }
    /**
     * ，遍历所有的帖子,并将帖子置顶
     */
    public String queryTopPost(Model model){
        List<PostArticle> list = postArticleDao.findAll();
        List trueList = new ArrayList();
        List falseList = new ArrayList();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getToppost().equals("true")){
                trueList.add(list.get(i));
            }else {
                falseList.add(list.get(i));
            }
        }
        model.addAttribute("trueList", trueList);
        model.addAttribute("falseList", falseList);
        return "mindex";
    }
}
