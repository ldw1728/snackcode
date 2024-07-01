package com.project.snackcode.entity;

import com.project.snackcode.entity.base.BaseEntity;
import jakarta.persistence.*;
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
    private String code_cntns;

    /** 설명 */
    @Column(name = "code_desc")
    private String desc;

    /** 공개여부 */
    @Column(name = "open_yn")
    private Boolean openYn;

}
