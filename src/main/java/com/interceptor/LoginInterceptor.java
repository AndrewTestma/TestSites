package com.interceptor;

import com.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Mr.Andrew
 * @Description:登录拦截器
 * @date 2018/4/9 13:09
 */
public class LoginInterceptor implements HandlerInterceptor{

    /**
     * @Description:Handler执行之前调用这个方法
     * @date:2018/4/9 13:12
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session=httpServletRequest.getSession();
        User user=(User)session.getAttribute("user");
        String url = httpServletRequest.getRequestURI();
        if(url.indexOf("login")>0){
            return true;
        }
        if(user!=null){
            return true;
        }
        httpServletRequest.getRequestDispatcher("/index/login").forward(httpServletRequest,httpServletResponse);
        return false;
    }
    /**
     * @Description:Handler执行之后，ModelAndView返回之前调用这个方法
     * @date:2018/4/9 13:12
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    /** 
     * @Description:Handler执行完成之后调用这个方法
     * @date:2018/4/9 13:12  
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
