package com.zgy.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.security.browser.support.SimpleResponse;
import com.zgy.security.core.properties.LoginType;
import com.zgy.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
自定义登录失败处理器，需要实现AuthenticationFailureHandler接口，也可以继承其他实现AuthenticationSuccessHandler接口的类。
在没有自定义登录失败处理器之前，SpringSecurity默认的失败处理器是当认证失败后，放回认证失败的信息的页面，
并让用户重新登录。
如果要想在自定义登录失败处理器中使用SpringSecurity默认的登录失败处理器可以继承SimpleUrlAuthenticationFailureHandler这个类
 */

@Component
//用法一：
//public class MyAuthenticationFailHandler implements AuthenticationFailureHandler{
//用法二：
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler{

    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationFailHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.info("登录失败");

        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //返回500状态码
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
        }else{
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
        }
    }
}
