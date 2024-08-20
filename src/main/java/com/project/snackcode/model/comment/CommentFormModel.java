package com.project.snackcode.model.comment;

import com.project.snackcode.entity.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class CommentFormModel {

    private Long id;

    @NotNull
    /** 게시물 고유번호 */
    private Long postId;

    /** 부모댓글 고유번호 */
    private Long prntId;

    /** 내용 */
    @NotBlank
    private String cntns;

    @NotNull
    /** 회원고유번호 */
    private Long memId;

    public Comment toEntity() {
        return Comment.builder()
                .postId(this.postId)
                .prntId(this.prntId)
                .cntns(this.cntns)
                .memId(this.memId)
                .build();
    }
}
