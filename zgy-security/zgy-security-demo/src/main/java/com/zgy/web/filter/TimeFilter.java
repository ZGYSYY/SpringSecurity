package com.zgy.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 自定义一个时间拦截器
 * 注意，拦截器只能单纯的拦截请求，是不能直接知道该请求请求的是哪个Controller以及Controller中的哪个方法
 */
/*
当使用@Component不用再另行做配置，但是会过滤所有的请求，
当不加@Component时需要写一个配置类，使用配置类有一个好处，就是可以指定拦截哪些请求的url
 */
//@Component
public class TimeFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TimeFilter 执行");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("TimeFilter 耗时："+(new Date().getTime()-start)+"毫秒");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter 销毁");
    }
}
