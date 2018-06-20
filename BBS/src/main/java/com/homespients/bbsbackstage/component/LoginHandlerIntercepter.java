package com.homespients.bbsbackstage.component;

import com.homespients.bbsbackstage.entity.User;
import com.homespients.bbsbackstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登陆检查
 */
public class LoginHandlerIntercepter implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String username = (String)request.getSession().getAttribute("username");
        String password = (String)request.getSession().getAttribute("password");

        List<User> list = userService.queryByName("username");
        if(username==null || password==null){
            //提醒用户输入完整登陆信息
            return false;
        }else if(list.size()==0){
            //提醒登陆出错的信息原因
            request.setAttribute("msg", "用户不存在，请重新输入");
            //返回登陆界面
            //request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        }else if(!list.get(0).getPassword().equals(password)){
            //提醒用户输入的密码不正确
            return false;
        }else return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}
