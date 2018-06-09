package com.homework.bbs.bbsdemo.service;

import com.homework.bbs.bbsdemo.dao.RequestArticleDao;
import com.homework.bbs.bbsdemo.entity.RequestArticle;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestArticleService {
    @Autowired
    private RequestArticleDao requestArticleDao;

    /** @Description 获取所有的文章列表 @Param @Return List<PostArticle> */
    public Result<RequestArticle> findAll(){

        return ResultUtil.success(requestArticleDao.findAll());
    }

    /*发帖*/
    public Object RequestArticles(RequestArticle requestArticle) {
        return requestArticleDao.save(requestArticle);
    }

    /*用户查看自己发布的所有帖子*/
    public Result<RequestArticle> findByAuthorId(Integer authorId){
        if(requestArticleDao.existsById(authorId)==false){
            return ResultUtil.error(1,"用户不存在");
        }
        return ResultUtil.success(requestArticleDao.findByAuthorId(authorId));
    }

    /*查看帖子详情*/
    public Result<RequestArticle> findByRequestId(Integer requestId){
        if(requestArticleDao.existsById(requestId)==false){
            return ResultUtil.error(1,"帖子不存在");
        }
        return ResultUtil.success(requestArticleDao.findByRequestId(requestId));
    }


    /*根据分类查询帖子列表*/
    public Result<RequestArticle> findByCategoryId(int categoryId){
        if(requestArticleDao.existsByCategoryId(categoryId)==false){
            return ResultUtil.error(1,"类别不存在");
        }
        return ResultUtil.success(requestArticleDao.findByCategoryId(categoryId));
    }

    /*删除帖子*/
    public Result<RequestArticle> deleteRequestArticle(Integer articleId){
        if(requestArticleDao.existsById(articleId)==false){
            return ResultUtil.error(1,"帖子不存在");
        }
        requestArticleDao.deleteById(articleId);
        return ResultUtil.success("帖子删除成功");
    }

}
