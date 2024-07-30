package com.project.snackcode.service;

import com.project.snackcode.entity.Post;
import com.project.snackcode.exception.BasicErrorException;
import com.project.snackcode.model.member.LoginContextHolder;
import com.project.snackcode.model.post.PostFormModel;
import com.project.snackcode.model.post.PostModel;
import com.project.snackcode.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * post 조회
     * @param postId
     * @return
     */
    @Transactional(readOnly = true)
    public PostModel selectModel(Long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        return post.toModel();
    }

    @Transactional(readOnly = true)
    public Page<PostModel> selectPageByCategoryId(Long cateId, Pageable pageable){
        Page<Post> posts = postRepository.findAllByCategory_IdOrderByIdDesc(cateId, pageable);
        return posts.map(Post::toModel);
    }

    /**
     * 카테고리별 전체 post조회
     * @param cateId
     * @return
     */
    @Transactional(readOnly = true)
    public List<PostModel> selectModelsByCategoryId(Long cateId){
        List<Post> posts = postRepository.findAllByCategory_Id(cateId);
        return posts.stream().map(Post::toModel).collect(Collectors.toList());
    }

    /**
     * post 저장
     * @param postFormModel
     */
    @Transactional
    public void save(PostFormModel postFormModel) {
        Post post = postFormModel.toEntity();
        postRepository.save(post);
    }

    /**
     * post 수정
     * @param postFormModel
     */
    @Transactional
    public void update(PostFormModel postFormModel) {

        Post post = postRepository.findById(postFormModel.getId()).orElseThrow();

        Long memId = LoginContextHolder.getLoginUser().getId();

        if (memId != post.getCategory().getMember().getId()) {
            throw BasicErrorException.builder().code("INCORRECT_MEM").msg("diff postMem and loginmem").build();
        }

        postFormModel.update(post);

    }

    /**
     * post 삭제
     * @param postId
     */
    @Transactional
    public void delete(Long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.delete(post);
    }

    /**
     * 카테고리별 전체 post 삭제
     * @param cateId
     */
    @Transactional
    public void deleteAllByCategoryId(Long cateId) {
        List<Post> posts = postRepository.findAllByCategory_Id(cateId);
        posts.forEach(post->{
            postRepository.delete(post);
        });
    }

}
