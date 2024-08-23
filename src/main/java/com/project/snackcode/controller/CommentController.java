package com.project.snackcode.controller;


import com.project.snackcode.model.comment.CommentFormModel;
import com.project.snackcode.model.comment.CommentModel;
import com.project.snackcode.model.member.LoginContextHolder;
import com.project.snackcode.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * @param commentFormModel
     * @return
     */
    @PostMapping("/cmmt")
    public ResponseEntity save(@Valid CommentFormModel commentFormModel) {

        commentFormModel.setMemId(LoginContextHolder.getLoginUser().getId());
        commentService.saveComment(commentFormModel);

        return ResponseEntity.ok().build();
    }

    /**
     * 코멘트 수정
     * @param commentFormModel
     * @return
     */
    @PatchMapping("/cmmt")
    public ResponseEntity update(CommentFormModel commentFormModel) {

        commentService.updateComment(commentFormModel);

        return ResponseEntity.ok().build();
    }

    /**
     * 코멘트 삭제
     * @param cmmtId
     * @return
     */
    @DeleteMapping("/cmmt/{cmmtId}")
    public ResponseEntity delete(@PathVariable Long cmmtId) {

        commentService.deleteComment(cmmtId);

        return ResponseEntity.noContent().build();
    }
}
