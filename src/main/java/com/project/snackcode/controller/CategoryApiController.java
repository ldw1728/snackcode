package com.project.snackcode.controller;

import com.project.snackcode.model.category.CategoryFormModel;
import com.project.snackcode.model.category.CategoryModel;
import com.project.snackcode.model.member.LoginContextHolder;
import com.project.snackcode.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    /**
     * 카테고리 조회
     * @param id
     * @return
     */
    @GetMapping({"/category", "/category/{id}"})
    public ResponseEntity list(@PathVariable(required = false) Long id){
        Long memId = LoginContextHolder.getLoginUser().getId();
        List<CategoryModel> categoryModels = categoryService.selectCategoryModels(memId, id);
        return ResponseEntity.ok(categoryModels);
    }

    /**
     * 카테고리 생성
     * @param categoryFormModel
     * @return
     */
    @PostMapping("/category")
    public ResponseEntity save(@Valid CategoryFormModel categoryFormModel){
        categoryFormModel.setMemId(LoginContextHolder.getLoginUser().getId());
        categoryService.save(categoryFormModel);
        return ResponseEntity.ok().build();
    }

    /**
     * 카테고리 수정
     * @param categoryFormModel
     * @return
     */
    @PatchMapping("/category")
    public ResponseEntity update(@RequestBody @Valid CategoryFormModel categoryFormModel){
        categoryService.update(categoryFormModel);
        return ResponseEntity.ok().build();
    }

    /**
     * 카테고리 삭제
     * @param cateId
     * @return
     */
    @DeleteMapping("/category/{cateId}")
    public ResponseEntity delete(@PathVariable Long cateId) {
        Long memId = LoginContextHolder.getLoginUser().getId();
        categoryService.delete(memId, cateId);
        return ResponseEntity.ok().build();
    }

}
