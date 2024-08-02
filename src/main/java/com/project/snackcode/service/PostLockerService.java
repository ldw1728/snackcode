package com.project.snackcode.service;

import com.project.snackcode.entity.PostLocker;
import com.project.snackcode.model.post.PostLockerModel;
import com.project.snackcode.model.member.LoginContextHolder;
import com.project.snackcode.repository.PostLockerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLockerService {

    private final PostLockerRepository postLockerRepository;

    @Transactional(readOnly = true)
    public PostLockerModel selectModel() {

        PostLocker postLocker = postLockerRepository.findByMemId(LoginContextHolder.getLoginUser().getId());

        if (postLocker == null) {
            return null;
        }

        return postLocker.toModel();
    }

    @Transactional
    public void save(Long postId) {
        Long memId = LoginContextHolder.getLoginUser().getId();
        PostLocker postLocker = postLockerRepository.findByMemId(memId);

        String postIdStr = postId + ":";

        if(postLocker == null){
            postLocker = PostLocker.builder().memId(memId).posts(postIdStr).build();
            postLockerRepository.save(postLocker);
        }
        else{
            if(!postLocker.getPosts().contains(postIdStr)){
                postLocker.updatePost(postLocker.getPosts() + postIdStr);
            }
        }
    }


}
