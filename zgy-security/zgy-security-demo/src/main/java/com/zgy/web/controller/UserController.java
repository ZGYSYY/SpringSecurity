package com.zgy.web.controller;

import com.zgy.dto.User;
import com.zgy.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    //用法一：普通传参
    /*@GetMapping("/user")
    public List<User> query(@RequestParam(name = "username",required = false,defaultValue = "LHM") String name){

        System.out.println("name:"+name);
        List<User> users = new ArrayList();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }*/

    //用法二：对象传参
    @GetMapping("/user")
    public List<User> query(UserQueryCondition condition){

        //将对象转成指定格式的字符串
        System.out.println("condition:"+ ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
}
