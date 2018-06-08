package com.homework.bbs.bbsdemo.service;

import com.homework.bbs.bbsdemo.dao.GoodArticleDao;
import com.homework.bbs.bbsdemo.entity.GoodArticle;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodArticleService {
    @Autowired
    private GoodArticleDao goodArticleDao;

    /*添加精华帖*/
    public Object addGoodArticle(GoodArticle goodArticle){
        return goodArticleDao.save(goodArticle);
    }

    /*查看 用户/管理员 精华帖*/
    public Result<GoodArticle> findByUserId(Integer userId){
        if(goodArticleDao.existsByUserId(userId)==false){
            return ResultUtil.error(1,"用户不存在");
        }
        return ResultUtil.success(goodArticleDao.findByUserId(userId));
    }

    /*删除精华帖*/
    public Result<GoodArticle> deleteGoodArticle(Integer goodId){
        if(goodArticleDao.existsById(goodId)==false){
            return ResultUtil.error(1,"此精华帖不存在");
        }
        goodArticleDao.deleteById(goodId);
        return ResultUtil.success("精华帖删除成功");
    }

}
