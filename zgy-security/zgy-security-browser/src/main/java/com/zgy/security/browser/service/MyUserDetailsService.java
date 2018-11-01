package com.zgy.security.browser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService{

    private final static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    //根据获取用户密码和权限列表
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        logger.info("登录的用户名为："+s);
        /*
        AuthorityUtils.commaSeparatedStringToAuthorityList("admin")：将权限字符串，转为SpringSecurity的权限对象
         */
        //方法一：获取密码和权限
//        return new User(s,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //方法二：获取密码、账户是否能使用、账户是否过期、密码是否过期、账户是否被锁定、权限列表
        String password = passwordEncoder.encode("123456");
        logger.info("用户密码为："+password);
        return new User(s,password,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
