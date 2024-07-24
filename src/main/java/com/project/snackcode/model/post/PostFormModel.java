package com.project.snackcode.model.post;

import com.project.snackcode.entity.Category;
import com.project.snackcode.entity.Post;
import com.project.snackcode.enums.OpenType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostFormModel {

    private Long id;

    @NotBlank
    private Long cateId;
    private Category category;

    @NotBlank
    private String title;

    @NotBlank
    private String codeCntns;

    @NotBlank
    private String desc;

    private OpenType openType;

    public Post toEntity(){
        return Post.builder()
                    .cateId(this.cateId)
                    .codeCntns(this.codeCntns)
                    .desc(this.desc)
                    .title(this.title)
                    .openType(OpenType.Y)
                    .build();
    }

    public void update(Post post){
        post.update(this.cateId, this.title, this.codeCntns, this.desc, this.openType);
    }

}
