package com.zgy.security.core.properties;

public class BrowserProperties {

    private String loginPage = "/default-login.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600; //配置记住我，默认3600秒

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
