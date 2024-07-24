package com.project.snackcode.controller;

import com.project.snackcode.model.post.PostFormModel;
import com.project.snackcode.model.post.PostModel;
import com.project.snackcode.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * post page
     * @return
     */
    @GetMapping("/post")
    public String list(){
        return "pages/post/list";
    }

    /**
     * post page, 단건 조회
     * @param cateId
     * @param postId
     * @return
     */
    @GetMapping({"/post/{cateId}", "/post/{cateId}/{postId}"})
    @ResponseBody
    public ResponseEntity<Page<PostModel>> select(  @PathVariable Long cateId,
                                   @PathVariable(required = false) Long postId,
                                   Pageable pageable){

        Page<PostModel> postModels = postService.selectPageByCategoryId(cateId, pageable);
        System.out.println(postModels.getTotalElements());

        return ResponseEntity.ok(postModels);

//        // category post 전체조회
//        if (postId == null) {
//            Page<PostModel> postModels = postService.selectPageByCategoryId(cateId, pageable);
//            return ResponseEntity.ok(postModels);
//        }
//
//        // 특정 post 조회
//        PostModel postModel = postService.selectModel(postId);
//        return ResponseEntity.ok(postModel);
    }

    /**
     * post 저장
     * @param postFormModel
     * @return
     */
    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity save(@Valid PostFormModel postFormModel){
        postService.save(postFormModel);
        return ResponseEntity.ok().build();
    }

    /**
     * post 수정
     * @param postFormModel
     * @return
     */
    @PatchMapping("/post")
    @ResponseBody
    public ResponseEntity update(@RequestBody @Valid PostFormModel postFormModel){
        postService.update(postFormModel);
        return ResponseEntity.ok().build();
    }

    /**
     * post 삭제
     * @param postId
     * @return
     */
    @DeleteMapping("/post/{postId}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }

}
