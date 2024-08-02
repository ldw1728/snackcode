package com.project.snackcode.controller;

import com.project.snackcode.model.post.PostModel;
import com.project.snackcode.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

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

    @GetMapping("/store")
    public String store(Model model){
        return "pages/store";
    }

}
