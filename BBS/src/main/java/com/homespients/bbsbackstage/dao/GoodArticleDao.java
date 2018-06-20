package com.homespients.bbsbackstage.dao;

import com.homespients.bbsbackstage.entity.GoodArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodArticleDao extends JpaRepository<GoodArticle,Integer> {
    /*查看 用户/管理员 精华帖*/
    public List<GoodArticle> findByUserId(int userId);
    /*用户/管理员是否存在*/
    public boolean existsByUserId(int userId);
}