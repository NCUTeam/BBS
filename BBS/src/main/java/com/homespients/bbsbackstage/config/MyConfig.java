package com.homespients.bbsbackstage.config;

//所有的webMvcConfigurerAdapter组件会一起起作用,扩充功能

import com.homespients.bbsbackstage.component.LoginHandlerIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

    //註冊到容器去
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/member").setViewName("member");
                registry.addViewController("/searchResult").setViewName("searchResult");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //拦截需要登陆的界面，检验密码是否正确
                /*registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**").
                        excludePathPatterns("/login.html", "/", "login");*/
            }
        };
        return adapter;
    }
}
