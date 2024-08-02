package com.project.snackcode.model.post;

import com.project.snackcode.model.post.PostModel;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostLockerModel {

    private Long id;

    private Long memId;

    private List<String> postIdList;

    private List<PostModel> postModelList;

    @Builder
    public PostLockerModel(Long id, Long memId, String posts) {
        this.id             = id;
        this.memId          = memId;
        this.postIdList     = Arrays.stream(posts.split(":")).collect(Collectors.toList());
    }
}
