package com.homespients.bbsbackstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JumpController {

    //前往发布帖子界面
    @GetMapping(value = "/wtiezi")
    public String toIssuePage(){
        return "wtiezi";
    }

    //前往搜索界面
    @GetMapping(value = "/tieziDetail")
    public String toSearchPage(){
        return "tieziDetail";
    }

    //前往话题广场界面
    @GetMapping(value = "/index")
    public String toTopicSquarePage(){
        return "index";
    }

    //前往需求广场
    @GetMapping(value = "/xqgc")
    public String toDemandSquarePage(){
        return "xqgc";
    }

    //前往互动广场
    @GetMapping(value = "/hdgc")
    public String toInteractiveSquarePage(){
        return "hdgc";
    }

    //前往精华帖
    @GetMapping(value = "/jht")
    public String toBetterPost(){
        return "jht";
    }

    //前往管理员登陆界面
    @GetMapping(value = "/login")
    public String toManagerPage(){
        return "login";
    }

    //前往搜索界面
    @GetMapping(value = "/search")
    public String toSearch(){
        return "search";
    }

    //前往member界面
//    @GetMapping(value = "/member")
//    public String toMemberPage(){
//        return "member";
//    }

    //前往文章页面
    @GetMapping(value = "/article")
    public String toArticlePage(){
        return "article";
    }

    @GetMapping(value = "/mindex")
    public String toMindex(){
        return "mindex";
    }

    @GetMapping(value = "/mxqgc")
    public String toMxqgc(){
        return "mxqgc";
    }

    @GetMapping(value = "/mhdgc")
    public String toMhdgc(){
        return "mhdgc";
    }

    @GetMapping(value = "/mjht")
    public String toMjht(){
        return "mjht";
    }

    @GetMapping(value = "/changetiezi")
    public String toChangetiezi(){
        return "changeteizi";
    }
}
