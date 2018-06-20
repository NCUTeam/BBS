package com.homespients.bbsbackstage.dao;

import com.homespients.bbsbackstage.entity.ReplyArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReplyArticleDao extends JpaRepository<ReplyArticle,Integer> {
    /*查询当前帖子的回复情况*/
    public List<ReplyArticle> findByArticleId(int articleId);

    /*根据帖子id删除对其的评论情况*/
    public void deleteByArticleId(int articleId);

    /*删除二级评论*/
    @Modifying
    @Transactional
    @Query(value = "delete from reply_article where reply_article.pid = ?1",nativeQuery = true)
    public void deleteByPid(Integer userId);
}
