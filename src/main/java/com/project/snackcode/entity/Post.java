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
    @JoinColumn(name = "cid")
    private Category category;

    /** 포스트명 */
    @Column(name = "name")
    private String name;

    /** 코드컨텐츠 */
    @Column(name = "code_cntns")
    private String codeCntns;

    /** 설명 */
    @Column(name = "code_desc")
    private String desc;

    /** 공개여부 */
    @Column(name = "open_yn")
    private OpenType openType;

    @Builder
    public Post(Long cateId, String name, String codeCntns, String desc, OpenType openType) {
        this.category   = Category.builder().id(cateId).build();
        this.name       = name;
        this.codeCntns  = codeCntns;
        this.desc       = desc;
        this.openType   = openType;
    }

    public void update(Long cateId, String name, String codeCntns, String desc, OpenType openYn) {
        this.category   = Category.builder().id(cateId).build();
        this.name       = name;
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
                        .name(this.name)
                        .openType(this.openType)
                        .regDt(this.regDt)
                        .updtDt(this.updtDt)
                        .build();
    }
}
