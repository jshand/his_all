package com.neuedu.framework;

import com.neuedu.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/21  10:27 21
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(HisConstants.LOGIN_USER);
        if(user!= null){//已经登录的用户，
            return true;
        }else{
            response.sendRedirect("login.html");
        }

        return false;
    }
}
