package com.zgy.security.browser.config;

import com.zgy.security.browser.authentication.MyAuthenticationFailHandler;
import com.zgy.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.zgy.security.core.properties.SecurityProperties;
import com.zgy.security.core.validate.code.ValidateCodeFilter;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/*
WebSecurityConfigurerAdapter:SpringSecurity适配器类，专门用来做安全配置的一个配置器
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    public MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) //添加过滤器
//                .httpBasic() //基本认证方式
                .formLogin() //表单验证的方式
                .loginPage("/authentication/require") //指定登录页面地址
                .loginProcessingUrl("/authentication/form") //指定登录提交地址
                .successHandler(myAuthenticationSuccessHandler) //设置自定义的登录成功处理器
                .failureHandler(myAuthenticationFailHandler) //设置自定义的登录失败处理器
                .and()
                .rememberMe() //配置记住我功能
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests() //对请求做一个授权
                .antMatchers("/authentication/require"
                        ,securityProperties.getBrowser().getLoginPage()
                        ,"/code/image").permitAll() //该请求不需要权限认证
                .anyRequest() //所有的请求
                .authenticated() //都要进行身份认证
                .and()
                .csrf().disable(); //关闭跨站请求保护
    }

    //注入密码校验
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //注入记住我功能的数据访问
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        /*
        自动在数据库中创建一个token和username对应的表，只能使用一次，如果表已经存在，就会报错
         */
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
