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

}
