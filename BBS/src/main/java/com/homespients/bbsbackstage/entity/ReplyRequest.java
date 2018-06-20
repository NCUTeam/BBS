package com.homespients.bbsbackstage.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/*
 * 回复需求帖实体
 * */
@Entity
@Table(name = "reply_request")
public class ReplyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer articleId;          //所属帖子

    private Integer userId;             //评论的用户的id

    private Integer pid=0;             //父评论，如果不设置，默认为0

    private Integer replyUserId;       //被回复者的id

    @CreationTimestamp  //由数据库自动创建时间
    private Timestamp createTime;

    @NotEmpty(message = "评论内容不能为空")
    @Size(max = 500,message = "评论内容不能多于500字符")
    private String articleComment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getArticleComment() {
        return articleComment;
    }

    public void setArticleComment(String articleComment) {
        this.articleComment = articleComment;
    }

    @Override
    public String toString() {
        return "ReplyArticle{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", userId=" + userId +
                ", pid=" + pid +
                ", replyUserId=" + replyUserId +
                ", createTime=" + createTime +
                ", articleComment='" + articleComment + '\'' +
                '}';
    }


}
