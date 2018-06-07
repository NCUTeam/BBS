package com.homework.bbs.bbsdemo.service;

import com.homework.bbs.bbsdemo.dao.PostArticleDao;
import com.homework.bbs.bbsdemo.dao.ReplyArticleDao;
import com.homework.bbs.bbsdemo.entity.ReplyArticle;
import com.homework.bbs.bbsdemo.entity.Result;
import com.homework.bbs.bbsdemo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
public class ReplyArticleService {
    @Autowired
    private ReplyArticleDao replyArticleDao;

    /*删除回帖*/
    @Transactional
    public Result<ReplyArticle> removeReplyArticle(Integer id){
        if(replyArticleDao.existsById(id)==false){
            return ResultUtil.error(1,"评论不存在");
        }

        replyArticleDao.deleteById(id);
        return ResultUtil.success("当前评论删除成功");
    }


    /*删除二级评论*/
    @Transactional
    public Result<ReplyArticle> deleteByPid(Integer userId){
        if(replyArticleDao.existsById(userId)==false){
            return ResultUtil.error(1,"此二级评论不存在");
        }
        replyArticleDao.deleteByPid(userId);
        return ResultUtil.success("此二级评论删除成功");
    }


    /*根据帖子id删除对其的评论情况*/
    @Transactional
    public Result<ReplyArticle>  deleteByArticleId(Integer articleId){
        if(replyArticleDao.existsById(articleId)==false){
            return ResultUtil.error(1,"帖子不存在");
        }
        replyArticleDao.deleteByArticleId(articleId);
        return ResultUtil.success("帖子的所有评论删除成功");
    }


    /*查看当前文章所有回帖及评论*/
    public Result<ReplyArticle> getReplyArticleByArticleId(Integer articleId){
        if(replyArticleDao.existsById(articleId)==false){
            return ResultUtil.error(1,"帖子不存在");
        }
        return ResultUtil.success(replyArticleDao.findByArticleId(articleId));
    }

    /*新增回帖或评论*/
    @Transactional
    public Object createReplyArticle(ReplyArticle replyArticle){
      return replyArticleDao.save(replyArticle);
    }


}
