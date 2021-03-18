package com.cookietest.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import com.jayway.jsonpath.JsonPath;

import javax.servlet.http.Cookie;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("测试读取cookie值")
    void getCookie() throws Exception{
        Cookie cookieu = new Cookie("username", "mr liu");
        //过期时间，单位是：秒（s）
        cookieu.setMaxAge(30 * 24 * 60 * 60);
        cookieu.setPath("/");
        //query
        MvcResult mvcResult = mockMvc.perform(get("/user/get")
                .cookie(cookieu)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content, equalTo("mr liu"));
    }

    @Test
    @DisplayName("测试读取cookie值失败")
    void getCookieFail() throws Exception{
        //query
        MvcResult mvcResult = mockMvc.perform(get("/user/get")
                //.cookie(cookieu)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content, equalTo(""));
    }


    @Test
    @DisplayName("测试写cookie值")
    void setCookie() throws Exception{
        String cookieValue="laoliu123aaa";
        //query
        MvcResult mvcResult = mockMvc.perform(get("/user/set?userName="+cookieValue)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content, equalTo(cookieValue));

    }

}