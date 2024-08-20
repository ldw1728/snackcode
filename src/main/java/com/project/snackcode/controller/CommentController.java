package com.project.snackcode.controller;


import com.project.snackcode.model.comment.CommentFormModel;
import com.project.snackcode.model.comment.CommentModel;
import com.project.snackcode.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 코멘트 조회
     *
     * @param postID
     * @return
     */
    @GetMapping("/cmmt/{postID}")
    public ResponseEntity commentList(@PathVariable Long postID) {
        List<CommentModel> list = commentService.selectCommentList(postID);
        return ResponseEntity.ok(list);
    }

    /**
     * 코멘트 저장
     * @param postId
     * @param cmmtId
     * @param commentFormModel
     * @return
     */
    @PostMapping({"/cmmt/{postId}", "/cmmt/{postId}/{cmmtId}"})
    public ResponseEntity save(@PathVariable Long postId,
                               @PathVariable(required = false) Long cmmtId,
                               CommentFormModel commentFormModel) {

        commentFormModel.setPostId(postId);
        commentFormModel.setPrntId(cmmtId);

        commentService.saveComment(commentFormModel);

        return ResponseEntity.ok().build();
    }

    /**
     * 코멘트 수정
     * @param cmmtId
     * @param commentFormModel
     * @return
     */
    @PatchMapping("/cmmt/{cmmtId}")
    public ResponseEntity  update(@PathVariable Long cmmtId,
                                    CommentFormModel commentFormModel) {

        commentFormModel.setId(cmmtId);
        commentService.updateComment(commentFormModel);

        return ResponseEntity.ok().build();
    }
}
