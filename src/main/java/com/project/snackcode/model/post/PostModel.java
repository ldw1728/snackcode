package com.project.snackcode.model.post;

import com.project.snackcode.entity.Category;
import com.project.snackcode.enums.OpenType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostModel {

    /** id */
    private Long id;

    /** 카테고리 */
    private Category category;

    /** 포스트명 */
    private String title;

    /** 코드컨텐츠 */
    private String codeCntns;

    /** 설명 */
    private String desc;

    /** 공개여부 */
    private OpenType openType;

    /** 등록일자 */
    private LocalDateTime regDt;

    /** 수정일자 */
    private LocalDateTime updtDt;

    @Builder
    public PostModel(Long id, Category category, String title, String codeCntns, String desc, OpenType openType, LocalDateTime regDt, LocalDateTime updtDt) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.codeCntns = codeCntns;
        this.desc = desc;
        this.openType = openType;
        this.regDt = regDt;
        this.updtDt = updtDt;
    }
}
