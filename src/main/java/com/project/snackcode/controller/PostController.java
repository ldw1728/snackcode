package com.project.snackcode.controller;

import com.project.snackcode.model.post.PostFormModel;
import com.project.snackcode.model.post.PostModel;
import com.project.snackcode.service.PostLockerService;
import com.project.snackcode.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostLockerService postLockerService;

    @ModelAttribute("biz")
    public Map<String, Object> commonModel(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("uri", request.getRequestURI());
        return map;
    }

    /**
     * post page, 단건 조회
     * @param cateId
     * @param postId
     * @return
     */
    @GetMapping({"/post", "/post/{cateId}", "/post/{cateId}/{postId}"})
    @ResponseBody
    public ResponseEntity select(  @PathVariable(required = false) Long cateId,
                                   @PathVariable(required = false) Long postId,
                                   String searchStr,
                                   String searchCntnsType,
                                   Pageable pageable){
        // 검색어로 조회
        if (postId == null && cateId == null) {
            Page<PostModel> postModels = postService.selectPageBySearch(searchStr, searchCntnsType,  pageable);
            return ResponseEntity.ok(postModels);
        }

        // category post 전체조회
        if (postId == null) {
            Page<PostModel> postModels = postService.selectPageByCategoryId(cateId, pageable);
            return ResponseEntity.ok(postModels);
        }

        // 특정 post 조회
        PostModel postModel = postService.selectModelWithIncreaseReadCnt(postId);
        return ResponseEntity.ok(postModel);
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
    public ResponseEntity update(@Valid PostFormModel postFormModel){
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

    /**
     * post like
     * @param postId
     * @return
     */
    @PostMapping("/post/like/{postId}")
    @ResponseBody
    public ResponseEntity like(@PathVariable Long postId) {
        int cnt = postService.increaseLikeCnt(postId);
        return ResponseEntity.ok(cnt);
    }

    /**
     * post store
     * @param postId
     * @return
     */
    @PostMapping("/post/store/{postId}")
    @ResponseBody
    public ResponseEntity store(@PathVariable Long postId) {
        postLockerService.save(postId);
        return ResponseEntity.ok().build();
    }

    /**
     * post storedPostœ
     * @return
     */
    @GetMapping("/post/store")
    @ResponseBody
    public ResponseEntity storedPosts(Pageable pageable) {
        List<PostModel> postModels = postService.selectPostLocker(pageable);
        return ResponseEntity.ok(postModels);
    }

}
