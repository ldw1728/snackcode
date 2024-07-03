package com.project.snackcode.model.category;

import com.project.snackcode.entity.Category;
import com.project.snackcode.entity.Member;
import com.project.snackcode.model.member.MemberModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class CategoryModel {

    /** 카테고리고유번호 */
    private Long id;

    /** 회원*/
    private Long memId;
    private MemberModel member;

    /** 부모 카테고리*/
    private CategoryModel prntCategory;

    /** 카테고리명 */
    private String name;

    /** 등록일자 */
    private LocalDateTime regDt;

    /** 수정일자 */
    private LocalDateTime updtDt;

    @Builder
    public CategoryModel(Long id, Long memId, MemberModel member, CategoryModel prntCategory, String name, LocalDateTime regDt, LocalDateTime updtDt) {
        this.id = id;
        this.memId = memId;
        this.member = member;
        this.prntCategory = prntCategory;
        this.name = name;
        this.regDt = regDt;
        this.updtDt = updtDt;
    }
}
