package com.project.snackcode.model.post;

import com.project.snackcode.entity.Category;
import com.project.snackcode.entity.Post;
import com.project.snackcode.enums.OpenType;
import lombok.Data;

@Data
public class PostFormModel {

    private Long id;

    private Long cateId;
    private Category category;

    private String name;

    private String codeCntns;

    private String desc;

    private OpenType openType;

    public Post toEntity(){
        return Post.builder()
                    .cateId(this.cateId)
                    .codeCntns(this.codeCntns)
                    .desc(this.desc)
                    .name(this.name)
                    .openType(OpenType.Y)
                    .build();
    }

    public void update(Post post){
        post.update(this.cateId, this.name, this.codeCntns, this.desc, this.openType);
    }

}
