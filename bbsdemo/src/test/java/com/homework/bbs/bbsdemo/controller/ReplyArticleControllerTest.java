package com.homework.bbs.bbsdemo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class ReplyArticleControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test
    public void removeReplyArticle() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/comments/user/3/pid/0?id=9")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getReplyArticleByArticleId() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/comments?articleId=1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createReplyArticle() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/comments")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}