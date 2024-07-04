package com.project.snackcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home(){
        return "home";
    }

    @GetMapping("/auth")
    @ResponseBody
    public String auth(){
        return "auth";
    }
}
