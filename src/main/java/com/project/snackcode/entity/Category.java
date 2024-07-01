package com.project.snackcode.entity;

import com.project.snackcode.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

/**
 *  TB_CATEGORY - 카테고리 테이블
 */

@Entity
@Table(name = "TB_CATEGORY")
@DynamicUpdate
@NoArgsConstructor
public class Category extends BaseEntity {

    /** 카테고리고유번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATE_ID")
    private Long id;

    /** 회원*/
    @ManyToOne
    @JoinColumn(name = "uid")
    private Member member;

    /** 부모 카테고리*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prnt_cid")
    private Category prntCategory;

    /** 카테고리명 */
    @Column(name = "NAME")
    private String name;


}
