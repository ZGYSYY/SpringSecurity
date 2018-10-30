package com.zgy.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

//基于SpringRunner来运行测试用例
@RunWith(SpringRunner.class)
//说明这是SpringBoot测试类
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    //模拟一个SpringMvc环境对象
    private MockMvc mockMvc;

    //在每个测试用例运行之前会先运行这个方法
    @Before
    public void setup(){
        //初始化SpringMvc环境
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        //用法对应UserController的List<User> query(UserQueryCondition condition)方法
        //用法一：普通传参
        /*mockMvc.perform(MockMvcRequestBuilders.get("/user") //发送一个get请求，请求地址为：/user
                .param("username","ZGY") //传递一个参数
                .contentType(MediaType.APPLICATION_JSON_UTF8)) //设置请求格式为JSON，编码为UTF-8
                .andExpect(MockMvcResultMatchers.status().isOk()) //断言，返回的状态码为200
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)); //断言，返回的是一个集合，并且长度为3*/

        //用法二：对象传参
        /*mockMvc.perform(MockMvcRequestBuilders.get("/user") //发送一个get请求，请求地址为：/user
                .param("username","ZGY") //传递一个参数
                .param("age","23")
                .param("ageTo","30")
                .param("xxx","yyy")
                .contentType(MediaType.APPLICATION_JSON_UTF8)) //设置请求格式为JSON，编码为UTF-8
                .andExpect(MockMvcResultMatchers.status().isOk()) //断言，返回的状态码为200
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));*/ //断言，返回的是一个集合，并且长度为3

        //用法三：测试JSonView
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user") //发送一个get请求，请求地址为：/user
                .param("username","ZGY") //传递一个参数
                .param("age","23")
                .param("ageTo","30")
                .param("xxx","yyy")
                .contentType(MediaType.APPLICATION_JSON_UTF8)) //设置请求格式为JSON，编码为UTF-8
                .andExpect(MockMvcResultMatchers.status().isOk()) //断言，返回的状态码为200
                .andReturn().getResponse().getContentAsString(); //获取返回的Json对象

        System.out.println("result："+result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        //用法一：测试普通传参，和参数匹配正则表达式
        /*mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("ZGY"));*/

        //用法二：测试JSonView
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("ZGY"))
                .andReturn().getResponse().getContentAsString(); //获取返回的Json对象

        System.out.println("result："+result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/aa")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError()); //断言，返回的状态码是一个4XX状态码
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        /*
        日期处理推荐：前台传参时，传时间戳，当用到时再做对应的转换，后台RESTFul返回数据默认日期是时间戳格式
         */
        Date birthday = new Date();
        String content = "{\"username\":\"ZGY\",\"password\":null,\"birthday\":"+birthday.getTime()+"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println("result："+result);
    }
}
