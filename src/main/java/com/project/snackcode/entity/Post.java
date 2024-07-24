package com.project.snackcode.entity;

import com.project.snackcode.entity.base.BaseEntity;
import com.project.snackcode.enums.OpenType;
import com.project.snackcode.model.post.PostModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

/**
 *  TB_POST - 포스트 테이블
 */

@Entity
@Table(name = "TB_POST")
@DynamicUpdate
@NoArgsConstructor
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

    /** 코드컨텐츠 */
    @Column(name = "CODE_CNTNS")
    private String codeCntns;

    /** 설명 */
    @Column(name = "CODE_DESC")
    private String desc;

    /** 공개여부 */
    @Column(name = "OPEN_YN")
    private OpenType openType;

    @Builder
    public Post(Long cateId, String title, String codeCntns, String desc, OpenType openType) {
        this.category   = Category.builder().id(cateId).build();
        this.title      = title;
        this.codeCntns  = codeCntns;
        this.desc       = desc;
        this.openType   = openType;
    }

    public void update(Long cateId, String title, String codeCntns, String desc, OpenType openYn) {
        this.category   = Category.builder().id(cateId).build();
        this.title       = title;
        this.codeCntns  = codeCntns;
        this.desc       = desc;
        this.openType   = openYn;
    }

    public PostModel toModel(){
        return PostModel.builder()
                        .id(this.id)
                        .category(this.category)
                        .codeCntns(this.codeCntns)
                        .desc(this.desc)
                        .title(this.title)
                        .openType(this.openType)
                        .regDt(this.regDt)
                        .updtDt(this.updtDt)
                        .build();
    }
}
