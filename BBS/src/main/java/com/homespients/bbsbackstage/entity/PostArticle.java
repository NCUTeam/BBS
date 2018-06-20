package com.homespients.bbsbackstage.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
/*
 * 发帖实体
 * */
@Entity
@Table(name = "post_article")
public class PostArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer articleId;   //帖子标识

    private Integer authorId ;      //作者id

    @NotEmpty(message = "标题不能为空")
    @Size(max = 200,message = "标题不能超过200个字符")
    @Column(name = "title", length = 200)
    private String title;           //标题名称

    @CreationTimestamp//由数据库自动创建时间
    private Timestamp createTime;       //发布时间

    @Lob //大对象，映射MYSQL的LongText类型
    @Basic(fetch = FetchType.LAZY)//懒加载
    @NotEmpty(message ="内容不能为空")
    @Size(max = 100000,message = "内容不能超过100000个字符")
    @Column(name = "article_content")
    private String articleContent;

    @Column(name = "category_id")
    private Integer categoryId;//类别id
    private String toppost;

    public String getToppost() {
        return toppost;
    }

    public void setToppost(String toppost) {
        this.toppost = toppost;
    }

    @Override
    public String toString() {
        return "PostArticle{" +
                "articleId=" + articleId +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", articleContent='" + articleContent + '\'' +
                ", categoryId=" + categoryId +
                ", toppost='" + toppost + '\'' +
                '}';
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}
