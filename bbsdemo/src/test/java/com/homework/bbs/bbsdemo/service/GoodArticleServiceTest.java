package com.homework.bbs.bbsdemo.service;

import com.homework.bbs.bbsdemo.entity.GoodArticle;
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
public class GoodArticleServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodArticleService goodArticleService;

    @Test
    public void addGoodArticle() {
        GoodArticle goodArticle = new GoodArticle();
        goodArticle.setArticleId(1);
        goodArticle.setUserId(3);
        goodArticleService.addGoodArticle(goodArticle);
    }

    @Test
    public void findByUserId() {
        logger.debug(goodArticleService.findByUserId(3).toString());
    }

    @Test
    public void deleteGoodArticle() {
        goodArticleService.deleteGoodArticle(1);
    }
}