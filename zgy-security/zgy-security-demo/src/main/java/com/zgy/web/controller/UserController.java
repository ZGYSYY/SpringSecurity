package com.zgy.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zgy.dto.User;
import com.zgy.dto.UserQueryCondition;
import com.zgy.exception.UserNotExistException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
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
    @GetMapping
    @JsonView(User.UserSimpleView.class) //指定JSonView视图解析对象
    public List<User> query(UserQueryCondition condition){

        //将对象转成指定格式的字符串
        System.out.println("condition:"+ ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList();
        users.add(new User());
        users.add(new User());
        users.add(new User());

        return users;
    }

    //用法一：传普通参数
//    @GetMapping("/user/{id}")
    //用法二：传递和正则表达式匹配的参数
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable long id){

//        throw new RuntimeException("user not exist");
//        throw new UserNotExistException(id+"");
        System.out.println("id："+id);
        User user = new User();
        user.setUsername("ZGY");

        return user;
    }

    @PostMapping
    //用法一：加入数据校验框架
    public User create(@Valid @RequestBody User user){
    //用法二：加入BindingResult类，让数据校验失败后，能继续执行方法，并把校验失败的数据保存在BindingResult对象中
//    public User create(@Valid @RequestBody User user, BindingResult errors){
        //输出数据校验失败的信息
        /*if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(x -> System.out.println(x.getDefaultMessage()));
        }*/

        System.out.println("user:"+ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");

        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors){
        //输出数据校验失败的信息
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(x -> System.out.println(x.getDefaultMessage()));
        }

        System.out.println("user:"+ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");

        return user;
    }

    @DeleteMapping("{id:\\d+}")
    public void delete(@PathVariable long id){
        System.out.println("Id:"+id);
    }
}
