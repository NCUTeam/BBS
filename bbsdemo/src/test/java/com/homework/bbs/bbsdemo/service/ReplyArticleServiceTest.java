package com.homework.bbs.bbsdemo.service;

import com.homework.bbs.bbsdemo.entity.ReplyArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReplyArticleServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReplyArticleService service;
    private ReplyArticle replyArticle;

    @Test
    public void removeReplyArticle() {
        service.removeReplyArticle(1);
    }
    @Test
    public void deleteByPid(){
        service.deleteByPid(2);
    }

    @Test
    public void deleteByArticleId() {
        service.deleteByArticleId(2);
    }

    @Test
    public void getReplyArticleByArticleId() {
        logger.debug(service.getReplyArticleByArticleId(2).toString());
    }

    @Test
    public void createReplyArticle() {
        ReplyArticle replyArticle = new ReplyArticle();
        replyArticle.setArticleId(1);
        replyArticle.setUserId(1);
        replyArticle.setArticleComment("123");
        service.createReplyArticle(replyArticle);
    }
}