package com.homework.bbs.bbsdemo.service;

import com.homework.bbs.bbsdemo.dao.PostArticleDao;
import com.homework.bbs.bbsdemo.entity.PostArticle;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class PostArticleService {
    @Autowired
    private PostArticleDao postArticleDao;

    /** @Description 获取所有的文章列表 @Param @Return List<PostArticle> */
    public Result<PostArticle> findAll(){

        return ResultUtil.success(postArticleDao.findAll());
    }

    /*发帖*/
    public Object PostArticles(PostArticle postArticle) {
       return postArticleDao.save(postArticle);
    }

    /*用户查看自己发布的所有帖子*/
    public Result<PostArticle> findByAuthorId(Integer authorId){
        if(postArticleDao.existsById(authorId)==false){
            return ResultUtil.error(1,"用户不存在");
        }
      return ResultUtil.success(postArticleDao.findByAuthorId(authorId));
    }

    /*查看帖子详情*/
    public Result<PostArticle> findByArticleId(Integer articleId){
        if(postArticleDao.existsById(articleId)==false){
            return ResultUtil.error(1,"帖子不存在");
        }
        return ResultUtil.success(postArticleDao.findByArticleId(articleId));
    }


    /*根据分类查询帖子列表*/
    public Result<PostArticle> findByCategoryId(int categoryId){
        if(postArticleDao.existsByCategoryId(categoryId)==false){
            return ResultUtil.error(1,"类别不存在");
        }
        return ResultUtil.success(postArticleDao.findByCategoryId(categoryId));
    }

    /*删除帖子*/
    public Result<PostArticle> deletePostArticle(Integer articleId){
        if(postArticleDao.existsById(articleId)==false){
            return ResultUtil.error(1,"帖子不存在");
        }
        postArticleDao.deleteById(articleId);
        return ResultUtil.success("帖子删除成功");
    }
}
