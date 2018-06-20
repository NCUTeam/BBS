package com.homespients.bbsbackstage.controller;

import com.homespients.bbsbackstage.entity.Category;
import com.homespients.bbsbackstage.entity.Result;
import com.homespients.bbsbackstage.service.CategoryService;
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
