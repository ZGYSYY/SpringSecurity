package com.zgy.security.browser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
WebSecurityConfigurerAdapter:SpringSecurity适配器类，专门用来做安全配置的一个配置器
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic() //基本认证方式
        http.formLogin() //表单验证的方式
                .and()
                .authorizeRequests() //对请求做一个授权
                .anyRequest() //所有的请求
                .authenticated(); //都要进行身份认证
    }

    //注入密码校验
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
