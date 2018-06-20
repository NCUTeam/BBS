package com.homespients.bbsbackstage.dao;

import com.homespients.bbsbackstage.entity.PostArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostArticleDao extends JpaRepository<PostArticle,Integer> {
    //用类别名称来查询  方法名有讲究不能乱写
    /*查询用户发布的所有帖子*/
    public List<PostArticle> findByAuthorId(int authorId);

    /*查询帖子详情*/
    public List<PostArticle> findByArticleId(int articleId);

    /*根据分类查询帖子列表*/
    public List<PostArticle> findByCategoryId(int categoryId);

    /*查询类别是否存在*/
    public boolean existsByCategoryId(int categoryId);

    @Query(value = "UPDATE post_article SET toppost = ? WHERE article_id = ?",nativeQuery = true)
    public void updateTopPost(String toppost, Integer id);
}
