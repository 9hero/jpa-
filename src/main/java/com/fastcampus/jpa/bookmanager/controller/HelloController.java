package com.fastcampus.jpa.bookmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hel(HttpServletResponse httpServletResponse){
        httpServletResponse.addCookie(new Cookie("",""));
        return "hello";
    }
    @GetMapping("/")
    public String main(){
        return "hello";
    }
}
