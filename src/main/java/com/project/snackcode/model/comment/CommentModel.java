package com.project.snackcode.model.comment;

import com.project.snackcode.model.member.MemberBaseModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentModel {

    private Long id;

    /** 게시물 고유번호 */
    private Long postId;

    /** 부모댓글 고유번호 */
    private Long prntId;

    /** 내용 */
    private String cntns;

    /** 회원고유번호 */
    private MemberBaseModel member;

    private LocalDateTime regDt;

    private LocalDateTime updtDt;

    private List<CommentModel> childComment = new ArrayList<>();

    @Builder
    public CommentModel(Long id, Long postId, Long prntId, String cntns, MemberBaseModel member, LocalDateTime regDt, LocalDateTime updtDt) {
        this.id     = id;
        this.postId = postId;
        this.prntId = prntId;
        this.cntns  = cntns;
        this.member = member;
        this.regDt  = regDt;
        this.updtDt = updtDt;
    }

    public void addChildComment(CommentModel commentModel) {
        this.childComment.add(commentModel);
    }
}
