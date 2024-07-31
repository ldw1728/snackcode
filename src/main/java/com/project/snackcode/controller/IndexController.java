package com.project.snackcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @ModelAttribute("biz")
    public Map<String, Object> commonModel(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("uri", request.getRequestURI());
        return map;
    }

    @GetMapping({"/", "/home"})
    public String home(){
        return "pages/home";
    }

    @GetMapping("/search")
    public String search(){
        return "pages/search";
    }


}
