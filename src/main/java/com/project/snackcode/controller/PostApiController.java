package com.project.snackcode.controller;

import com.project.snackcode.model.post.PostFormModel;
import com.project.snackcode.model.post.PostModel;
import com.project.snackcode.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    /**
     * post 조회
     * @param cateId
     * @param postId
     * @return
     */
    @GetMapping("/post/{cateId}/{postId}")
    public ResponseEntity list(@PathVariable Long cateId,
                               @PathVariable Long postId){

        // category post 전체조회
        if (postId == null) {
            List<PostModel> postModels = postService.selectModelsByCategoryId(cateId);
            return ResponseEntity.ok(postModels);
        }

        // 특정 post 조회
        PostModel postModel = postService.selectModel(postId);
        return ResponseEntity.ok(postModel);
    }

    /**
     * post 저장
     * @param postFormModel
     * @return
     */
    @PostMapping("/post")
    public ResponseEntity save(@RequestBody PostFormModel postFormModel){
        postService.save(postFormModel);
        return ResponseEntity.ok().build();
    }

    /**
     * post 수정
     * @param postFormModel
     * @return
     */
    @PatchMapping("/post")
    public ResponseEntity update(@RequestBody PostFormModel postFormModel){
        postService.update(postFormModel);
        return ResponseEntity.ok().build();
    }

    /**
     * post 삭제
     * @param postId
     * @return
     */
    @DeleteMapping("/post/{postId}")
    public ResponseEntity delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }

}
