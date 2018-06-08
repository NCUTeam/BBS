package com.homework.bbs.bbsdemo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
* 精华帖实体
* */

@Entity
@Table(name = "good_article")
public class GoodArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "good_id")
    private Integer goodId;      //精华帖唯一标识

    @NotNull(message = "文章id不能为空")
    private Integer articleId;   //文章id

    @NotNull(message = "添加精华帖的用户id不能为空")
    private Integer userId ;     //添加精华帖的用户id

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GoodArticle{" +
                "goodId=" + goodId +
                ", articleId=" + articleId +
                ", userId=" + userId +
                '}';
    }
}
