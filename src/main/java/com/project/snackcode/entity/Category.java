package com.project.snackcode.entity;

import com.project.snackcode.entity.base.BaseEntity;
import com.project.snackcode.model.category.CategoryModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

/**
 *  TB_CATEGORY - 카테고리 테이블
 */

@Entity
@Table(name = "TB_CATEGORY")
@DynamicUpdate
@NoArgsConstructor
@Getter
public class Category extends BaseEntity {

    /** 카테고리고유번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATE_ID")
    private Long id;

    /** 회원*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEM_ID")
    private Member member;

    /** 부모 카테고리*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRNT_CID")
    private Category prntCategory;

    /** 카테고리명 */
    @Column(name = "NAME")
    private String name;

    @Builder
    public Category(Long id, Member member, Category prntCategory, String name) {
        this.id             = id;
        this.member         = member;
        this.prntCategory   = prntCategory;
        this.name           = name;
    }

    public void update(String name){
        this.name = name;
    }

    public CategoryModel toModel(){
        return CategoryModel.builder()
                            .id(this.id)
                            .memId(this.member.getId())
                            .prntCategory(
                                    this.prntCategory == null ? null : this.prntCategory.toModel()
                            )
                            .name(this.name)
                            .regDt(this.getRegDt())
                            .updtDt(this.getUpdtDt())
                            .build();
    }
}
