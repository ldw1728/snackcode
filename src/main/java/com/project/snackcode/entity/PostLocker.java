package com.project.snackcode.entity;

import com.project.snackcode.model.PostLockerModel;
import com.project.snackcode.model.post.PostModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_POST_LOCKER")
@NoArgsConstructor
@Getter
public class PostLocker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCK_ID")
    private Long id;

    @Column(name = "MEM_ID")
    private Long memId;

    @Column(name = "POSTS")
    private String posts;

    @Builder
    public PostLocker(Long memId, String posts) {
        this.memId = memId;
        this.posts = posts;
    }

    public PostLockerModel toModel() {
        return PostLockerModel.builder()
                .id(this.id)
                .memId(this.memId)
                .posts(this.posts)
                .build();
    }

    public void updatePost(String posts){
        this.posts = posts;
    }
}
