package com.project.snackcode.model.post;

import com.project.snackcode.entity.Category;
import com.project.snackcode.entity.Post;
import com.project.snackcode.enums.OpenType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostFormModel {

    private Long id;

    @NotNull
    private Long cateId;
    private Category category;

    @NotBlank
    private String title;

    @NotBlank
    private String cntnsType;

    @NotBlank
    private String codeCntns;

    private String desc;

    private OpenType openType;

    public Post toEntity(){
        return Post.builder()
                    .cateId(this.cateId)
                    .cntnsType(this.cntnsType)
                    .codeCntns(this.codeCntns)
                    .desc(this.desc)
                    .title(this.title)
                    .openType(this.openType)
                    .build();
    }

    public void update(Post post){
        post.update(this.cateId, this.title, this.cntnsType, this.codeCntns, this.desc, this.openType);
    }

}
