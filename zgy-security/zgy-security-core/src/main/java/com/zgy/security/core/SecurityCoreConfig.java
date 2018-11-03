package com.zgy.security.core;

import com.zgy.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
//让SecurityProperties配置读取器生效
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {


}
