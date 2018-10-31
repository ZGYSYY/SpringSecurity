package com.zgy.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自定义一个过滤器，过滤器在拦截器后才执行，因此可以知道请求调用了哪个Controller中的哪个方法
 */

@Component
public class TimeInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("TimeInterceptor preHandle");
        System.out.println("Controller："+((HandlerMethod)o).getBean().getClass().getName());
        System.out.println("method："+((HandlerMethod)o).getMethod().getName());
        httpServletRequest.setAttribute("start",new Date().getTime());
        return true; //如果是false，那么postHandle方法和afterCompletion不会执行
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("TimeInterceptor postHandle");
        long start = (long) httpServletRequest.getAttribute("start");
        System.out.println("访问耗时："+(new Date().getTime()-start)+"毫秒");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("TimeInterceptor afterCompletion");
        long start = (long) httpServletRequest.getAttribute("start");
        System.out.println("访问耗时："+(new Date().getTime()-start)+"毫秒");

        //注意，如果本来Controller有异常信息，但是在该方法了异常为空，是因为@ControllerAdvice注解的方法会先捕获对应的异常，
        //并处理掉，如果没有被捕获，就不为空
        System.out.println("异常信息："+e);
    }
}
