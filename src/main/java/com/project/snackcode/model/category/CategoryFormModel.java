package com.project.snackcode.model.category;

import com.project.snackcode.entity.Category;
import com.project.snackcode.entity.Member;
import lombok.Data;

@Data
public class CategoryFormModel {

    /** id */
    private Long id;

    /** 회원 */
    private Long memId;

    /** 부모 카테고리 */
    private Long prntCid;

    /** 카테고리명 */
    private String name;

    public Category toEntity(){
        return Category.builder()
                        .member(Member.builder().id(this.memId).build())
                        .prntCategory(this.prntCid != null ? Category.builder().id(this.prntCid).build() : null)
                        .name(this.name)
                        .build();
    }

    public void update(Category category) {
        category.update(this.name);
    }

}
