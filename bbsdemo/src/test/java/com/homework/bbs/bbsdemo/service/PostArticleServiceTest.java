package com.homework.bbs.bbsdemo.service;

import com.homework.bbs.bbsdemo.entity.PostArticle;
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
public class PostArticleServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PostArticleService service;
    private PostArticle postArticle;

    @Test
    public void findAll() {
        logger.debug(service.findAll().toString());
    }

    @Test
    public void postArticles() {
        PostArticle postArticle = new PostArticle();
        postArticle.setAuthorId(2);
        postArticle.setTitle("Title,Title");
        postArticle.setArticleContent("ContentContent");
        service.PostArticles(postArticle);
    }

    @Test
    public void findByAuthorId() {
        logger.debug(service.findByAuthorId(1).toString());
    }

    @Test
    public void deletePostArticle() {
        service.deletePostArticle(1);
    }
}