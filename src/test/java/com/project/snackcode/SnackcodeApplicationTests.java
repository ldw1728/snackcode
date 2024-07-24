package com.project.snackcode;

import com.project.snackcode.entity.Category;
import com.project.snackcode.model.category.CategoryFormModel;
import com.project.snackcode.model.member.LoginContextHolder;
import com.project.snackcode.model.post.PostFormModel;
import com.project.snackcode.model.post.PostModel;
import com.project.snackcode.service.CategoryService;
import com.project.snackcode.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class SnackcodeApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;


    @Test
    public void postTest(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<PostModel> postModels = postService.selectPageByCategoryId(2L, pageable);

        System.out.println(postModels.getTotalElements());

    }

    @Test
    @Commit
    public void categorySaveTest(){


        CategoryFormModel categoryFormModel = new CategoryFormModel();

        categoryFormModel.setMemId(1L);
        categoryFormModel.setName("thread");
        categoryFormModel.setPrntCid(4L);

        categoryService.save(categoryFormModel);

//        categoryFormModel.setMemId(1L);
//        categoryFormModel.setName("string");
//        categoryFormModel.setPrntCid();
//
//        categoryService.save(categoryFormModel);

    }

    @Test
    @Commit
    public void categorydeleteTest(){
        categoryService.delete(1L, 5L);
    }


    @Test
    @Commit
    public void postSaveTest(){
        PostFormModel postFormModel = new PostFormModel();

        postFormModel.setTitle("integer to string");
        postFormModel.setCodeCntns("public static string ......");
        postFormModel.setCateId(6L);
        postFormModel.setDesc("숫자에서 문자열 변환입니다.");

        postService.save(postFormModel);

    }

}
