package com.project.snackcode.entity;

import com.project.snackcode.entity.base.BaseEntity;
import com.project.snackcode.enums.OpenType;
import com.project.snackcode.model.post.PostModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

/**
 *  TB_POST - 포스트 테이블
 */

@Entity
@Table(name = "TB_POST")
@DynamicUpdate
@NoArgsConstructor
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    /** 카테고리 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATE_ID")
    private Category category;

    /** 포스트명 */
    @Column(name = "TITLE")
    private String title;

    /** 컨텐츠 타입 */
    @Column(name = "CNTNS_TYPE")
    private String cntnsType;

    /** 컨텐츠 */
    @Column(name = "CODE_CNTNS")
    private String codeCntns;

    /** 설명 */
    @Column(name = "CODE_DESC")
    private String desc;

    /** 공개여부 */
    @Column(name = "OPEN_YN")
    private OpenType openType;

    @Column(name = "READ_CNT")
    private Integer readCnt;

    @Column(name = "LIKE_CNT")
    private Integer likeCnt;

    @Builder
    public Post(Long cateId, String title, String cntnsType, String codeCntns, String desc, OpenType openType) {
        this.category   = Category.builder().id(cateId).build();
        this.title      = title;
        this.cntnsType  = cntnsType;
        this.codeCntns  = codeCntns;
        this.desc       = desc;
        this.openType   = openType;
        this.readCnt    = 0;
        this.likeCnt    = 0;
    }

    public void update(Long cateId, String title, String cntnsType, String codeCntns, String desc, OpenType openYn) {
        this.category   = Category.builder().id(cateId).build();
        this.title      = title;
        this.cntnsType  = cntnsType;
        this.codeCntns  = codeCntns;
        this.desc       = desc;
        this.openType   = openYn;
    }

    public void increaseReadCnt() {
        this.readCnt += 1;
    }

    public void increaseLikeCnt() {
        this.likeCnt += 1;
    }

    public PostModel toModel(){
        return PostModel.builder()
                        .id(this.id)
                        .category(this.category)
                        .cntnsType(this.cntnsType)
                        .codeCntns(this.codeCntns)
                        .desc(this.desc)
                        .title(this.title)
                        .openType(this.openType)
                        .readCnt(this.readCnt)
                        .likeCnt(this.likeCnt)
                        .regDt(this.regDt)
                        .updtDt(this.updtDt)
                        .build();
    }
}
