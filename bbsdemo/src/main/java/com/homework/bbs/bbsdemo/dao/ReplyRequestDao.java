package com.homework.bbs.bbsdemo.dao;

import com.homework.bbs.bbsdemo.entity.ReplyArticle;
import com.homework.bbs.bbsdemo.entity.ReplyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReplyRequestDao extends JpaRepository<ReplyRequest,Integer> {
    /*查询当前帖子的回复情况*/
    public List<ReplyRequest> findByArticleId(int articleId);

    public boolean existsByArticleId(int articleId);

    /*根据帖子id删除对其的评论情况*/
    public void deleteByArticleId(int articleId);

    /*删除二级评论*/
    @Modifying
    @Transactional
    @Query(value = "delete from reply_request where reply_request.pid = ?1",nativeQuery = true)
    public void deleteByPid(Integer userId);
}
