package com.homework.bbs.bbsdemo.dao;

import com.homework.bbs.bbsdemo.entity.PostArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PostArticleDao extends JpaRepository<PostArticle,Integer> {
    //用类别名称来查询  方法名有讲究不能乱写
    /*查询用户发布的所有帖子*/
    public List<PostArticle> findByAuthorId(int authorId);

    /*查询帖子详情*/
    public List<PostArticle> findByArticleId(int articleId);
}
