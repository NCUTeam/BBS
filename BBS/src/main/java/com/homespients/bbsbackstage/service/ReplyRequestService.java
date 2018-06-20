package com.homespients.bbsbackstage.service;

import com.homespients.bbsbackstage.dao.ReplyRequestDao;
import com.homespients.bbsbackstage.entity.ReplyRequest;
import com.homespients.bbsbackstage.entity.Result;
import com.homespients.bbsbackstage.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReplyRequestService {
    @Autowired
    private ReplyRequestDao replyRequestDao;
    /*删除回帖*/
    @Transactional
    public Result<ReplyRequest> removeReplyRequest(Integer id){
        if(replyRequestDao.existsById(id)==false){
            return ResultUtil.error(1,"评论不存在");
        }

        replyRequestDao.deleteById(id);
        return ResultUtil.success("当前评论删除成功");
    }


    /*删除二级评论*/
    @Transactional
    public Result<ReplyRequest> deleteByPid(Integer userId){
        if(replyRequestDao.existsById(userId)==false){
            return ResultUtil.error(1,"此二级评论不存在");
        }
        replyRequestDao.deleteByPid(userId);
        return ResultUtil.success("此二级评论删除成功");
    }


    /*根据帖子id删除对其的评论情况*/
    @Transactional
    public Result<ReplyRequest>  deleteByArticleId(Integer articleId){
        if(replyRequestDao.existsById(articleId)==false){
            return ResultUtil.error(1,"帖子不存在");
        }
        replyRequestDao.deleteByArticleId(articleId);
        return ResultUtil.success("帖子的所有评论删除成功");
    }


    /*查看当前文章所有回帖及评论*/
    public Result<ReplyRequest> getReplyRequestByArticleId(Integer articleId){
        if(replyRequestDao.existsByArticleId(articleId)==false){
            return ResultUtil.error(1,"帖子不存在");
        }
        return ResultUtil.success(replyRequestDao.findByArticleId(articleId));
    }

    /*新增回帖或评论*/
    @Transactional
    public Object createReplyRequest(ReplyRequest replyRequest){
        return replyRequestDao.save(replyRequest);
    }


}