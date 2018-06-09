package com.homework.bbs.bbsdemo.dao;

import com.homework.bbs.bbsdemo.entity.PostArticle;
import com.homework.bbs.bbsdemo.entity.RequestArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestArticleDao extends JpaRepository<RequestArticle,Integer> {
    /*查询用户发布的所有帖子*/
    public List<RequestArticle> findByAuthorId(int authorId);

    /*查询帖子详情*/
    public List<RequestArticle> findByRequestId(int requestId);

    /*根据分类查询帖子列表*/
    public List<RequestArticle> findByCategoryId(int categoryId);

    /*查询类别是否存在*/
    public boolean existsByCategoryId(int categoryId);
}
