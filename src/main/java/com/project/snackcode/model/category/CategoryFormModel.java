package com.project.snackcode.model.category;

import com.project.snackcode.entity.Category;
import com.project.snackcode.entity.Member;
import lombok.Data;

@Data
public class CategoryFormModel {

    /** 회원*/
    private Long memId;

    /** 부모 카테고리*/
    private Long prntCid;

    /** 카테고리명 */
    private String name;

    public Category toEntity(){
        return Category.builder()
                        .member(Member.builder().id(this.memId).build())
                        .prntCategory(Category.builder().Id(this.prntCid).build())
                        .name(this.name)
                        .build();
    }

}
