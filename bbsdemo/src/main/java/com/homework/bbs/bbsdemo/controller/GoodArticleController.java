package com.homework.bbs.bbsdemo.controller;

import com.homework.bbs.bbsdemo.entity.GoodArticle;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.service.GoodArticleService;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class GoodArticleController {
    @Autowired
    private GoodArticleService goodArticleService;

    /*添加精华帖*/
    @PostMapping(value = "/good_article")
    @Transactional
    public Result<GoodArticle> PostArticles(@Valid GoodArticle goodArticle, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        goodArticleService.addGoodArticle(goodArticle);
        return ResultUtil.success("精华帖添加成功");
    }

    /*查看 用户/管理员 精华帖*/
    @GetMapping(value = "/user/{user_id}/good_article")
    public Result<GoodArticle> findByUserId(@PathVariable("user_id") Integer userId){
        return goodArticleService.findByUserId(userId);
    }

    /*删除精华帖*/
    @DeleteMapping(value = "/good_article/{good_id}")
    @Transactional
    public Result<GoodArticle> deleteGoodArticle(@PathVariable("good_id") Integer goodId){
        return goodArticleService.deleteGoodArticle(goodId);
    }

}
