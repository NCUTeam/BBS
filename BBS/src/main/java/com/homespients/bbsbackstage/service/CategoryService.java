package com.homespients.bbsbackstage.service;

import com.homespients.bbsbackstage.dao.CategoryDao;
import com.homespients.bbsbackstage.entity.Category;
import com.homespients.bbsbackstage.entity.Result;
import com.homespients.bbsbackstage.util.ResultUtil;
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
