package com.homework.bbs.bbsdemo.config;

//所有的webMvcConfigurerAdapter组件会一起起作用,扩充功能

import com.homework.bbs.bbsdemo.component.LoginHandlerIntercepter;
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
                /*registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");*/
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
