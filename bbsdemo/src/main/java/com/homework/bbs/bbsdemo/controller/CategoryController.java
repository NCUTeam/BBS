package com.homework.bbs.bbsdemo.controller;

import com.homework.bbs.bbsdemo.entity.Category;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /** @Description 获取所有的分类*/
    @GetMapping(value = "/Category")
    public Result<Category> findAll()
    {
        return categoryService.findAll();
    }
}
