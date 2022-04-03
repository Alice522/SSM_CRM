package com.fj.crm.settings.web.interceptor;

import com.fj.crm.commons.contants.Contants;
import com.fj.crm.settings.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    /*
    * 验证用户是否登录
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //尝试从session中获取user，若获取不到，则用户没有登录
        HttpSession httpSession = request.getSession();
        User user =(User) httpSession.getAttribute(Contants.SESSION_USER);
        if(user == null){
            //用户没有登录，重定向到登录页面，并拦截请求
            response.sendRedirect(request.getContextPath());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
