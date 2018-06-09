package com.homework.bbs.bbsdemo.service;

import com.homework.bbs.bbsdemo.dao.CategoryDao;
import com.homework.bbs.bbsdemo.entity.Category;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    /** @Description 获取所有的分类 */
    public Result<Category> findAll() {

        return ResultUtil.success(categoryDao.findAll());
    }
}
