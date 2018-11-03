package com.zgy.security.core.properties;
/*
图片验证码配置类
 */
public class ImageCodeProperties {

    private int width = 68; //验证码图片宽度
    private int height = 24; //验证码图片高度
    private int length = 4; //验证码位数
    private int expireIn = 60; //验证码过期时间
    private String url; //验证码拦截请求地址

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
