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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class PostArticleControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void findAll() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/post_article")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void postArticles() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/post_article")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findByArticleId() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/post_article/articleId/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findByAuthorId() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/user/1/post_article")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deletePostArticle() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/post_article?articleId=1")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}