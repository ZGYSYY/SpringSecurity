package com.zgy.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class User {

    /*
    使用JsonView，分别定义一个UserSimpleView接口和UserDetailView，
    UserSimpleView：只返回用户名；
    UserDetailView：返回用户名和密码
    要想使用JsonView，需要在成员变量的get方法上声明对应的视图接口
     */
    public interface UserSimpleView{}
    public interface UserDetailView extends UserSimpleView{}

    private String username;
    @NotBlank //基于hibernate的数据校验框架，这里表示密码不能为空，要想该注解起反应，需要在对象的controller的参数中使用@Valid
    private String password;
    private String id;
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
