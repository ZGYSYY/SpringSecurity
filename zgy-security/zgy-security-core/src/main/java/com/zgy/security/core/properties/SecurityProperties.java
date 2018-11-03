package com.zgy.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

//会读取配置文件中以“zgy.security”开头的配置项，并将所配置的值设置到该对象中
@ConfigurationProperties(prefix = "zgy.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
