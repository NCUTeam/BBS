package com.homework.bbs.bbsdemo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class GoodArticleControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void postArticles() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/good_article")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findByUserId() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/user/3/good_article")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteGoodArticle() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/good_article/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}