package com.zgy.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.security.core.properties.LoginType;
import com.zgy.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
自定义登录成功处理器,需要实现AuthenticationSuccessHandler接口，也可以继承其他实现AuthenticationSuccessHandler接口的类。
在没有自定义登录成功处理器之前，SpringSecurity默认的登录成功处理器是访问登录请求，如果认证失败，
会返回认证失败的信息页面，并让用户重新登录，当用户登录成功后，则完成登录请求。
如果要想在自定义登录成功处理器中使用SpringSecurity默认的成功处理器可以继承SavedRequestAwareAuthenticationSuccessHandler这个类
 */

@Component
//用法一：
//public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
//用法二：
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    /*
    一个可以将对象转成json串的类，在spring加载的时候会自动容器化一个ObjectMapper的对象，
    所以可以直接使用。
     */
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功！");

        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
