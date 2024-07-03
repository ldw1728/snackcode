package com.project.snackcode.controller;

import com.project.snackcode.model.category.CategoryFormModel;
import com.project.snackcode.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity save(@RequestBody CategoryFormModel categoryFormModel){

        //TODO 카테고리 저장 로직 구현
        System.out.println(categoryFormModel);

        return ResponseEntity.ok().build();

    }



}
