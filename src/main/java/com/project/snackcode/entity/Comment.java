package com.project.snackcode.entity;


import com.project.snackcode.entity.base.BaseEntity;
import com.project.snackcode.model.comment.CommentModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TB_COMMENT")
@DynamicUpdate
@NoArgsConstructor
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CMMT_ID")
    private Long id;

    /** 게시물 고유번호 */
    private Long postId;

    /** 부모댓글 고유번호 */
    private Long prntId;

    /** 내용 */
    private String cntns;

    /** 회원고유번호 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEM_ID")
    private Member member;

    @Builder
    public Comment(Long postId, Long prntId, String cntns, Long memId) {
        this.postId = postId;
        this.prntId = prntId;
        this.cntns  = cntns;
        this.member = Member.builder().id(memId).build();
    }

    /** 수정 */
    public void update(String cntns) {
        this.cntns = cntns;
    }

    public CommentModel toModel() {
        return CommentModel.builder()
                .id(this.id)
                .postId(this.postId)
                .prntId(this.prntId)
                .cntns(this.cntns)
                .member(this.member.toBasicModel())
                .regDt(this.regDt)
                .updtDt(this.updtDt)
                .build();
    }


}
