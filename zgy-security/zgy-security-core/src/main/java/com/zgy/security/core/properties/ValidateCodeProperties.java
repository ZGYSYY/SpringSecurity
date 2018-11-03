package com.zgy.security.core.properties;
/*
验证码配置类
 */
public class ValidateCodeProperties {

    ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
