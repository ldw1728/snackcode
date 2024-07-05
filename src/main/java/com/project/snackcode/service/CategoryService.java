package com.project.snackcode.service;

import com.project.snackcode.entity.Category;
import com.project.snackcode.model.category.CategoryFormModel;
import com.project.snackcode.model.category.CategoryModel;
import com.project.snackcode.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final PostService postService;

    /**
     * 카테고리 조회
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public CategoryModel selectModel(Long id){
        Category category = categoryRepository.findById(id).get();

        if (category == null) {
            return null;
        }

        return category.toModel();
    }

    /**
     * 최상위 카테고리 리스트 조회
     * @param memId
     * @return
     */
    @Transactional(readOnly = true)
    public List<CategoryModel> selectRootCategoryModels(Long memId){
        List<Category> categorys = categoryRepository.findAllByMember_IdAndPrntCategory_Id(memId, null);
        return categorys.stream().map(Category::toModel).collect(Collectors.toList());
    }

    /**
     * 카테고리 저장
     * @param categoryFormModel
     */
    @Transactional
    public void save(CategoryFormModel categoryFormModel){
        Category category = categoryFormModel.toEntity();
        categoryRepository.save(category);
    }

    /**
     * 카테고리 수정
     * @param categoryFormModel
     */
    @Transactional
    public void update(CategoryFormModel categoryFormModel) {
        Category category = categoryRepository.findById(categoryFormModel.getId()).orElseThrow();
        categoryFormModel.update(category);
    }

    /**
     * 카테고리 삭제
     * @param cateId
     */
    @Transactional
    public void delete(Long cateId){
        Category category = categoryRepository.findById(cateId).orElseThrow();
        postService.deleteAllByCategoryId(cateId);
        categoryRepository.delete(category);
    }

    /**
     * 회원 전체 카테고리 삭제
     * @param memId
     */
    @Transactional
    public void deleteAll(Long memId){
        List<Category> categoryList = categoryRepository.findAllByMember_Id(memId);
        categoryList.forEach(category -> {
            postService.deleteAllByCategoryId(category.getId());
            categoryRepository.delete(category);
        });
    }



}
