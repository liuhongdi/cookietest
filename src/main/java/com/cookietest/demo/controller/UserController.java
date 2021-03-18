package com.cookietest.demo.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    //读取session
    @GetMapping("/get")
    public String getcookie(@CookieValue(value = "username", defaultValue = "") String username) {
        System.out.println("get cookie:"+username);
        return "" + username;
    }

    //设置session
    @GetMapping("/set")
    public String setcookie(@RequestParam("userName")String userName, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("username", userName);
        //过期时间，单位是：秒（s）
        cookie.setMaxAge(30 * 24 * 60 * 60);
        //cookie.setPath(request.getContextPath());
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("set cookie:"+userName);

        return userName;
    }


}
