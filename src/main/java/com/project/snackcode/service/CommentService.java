package com.project.snackcode.service;

import com.project.snackcode.entity.Comment;
import com.project.snackcode.exception.BasicErrorException;
import com.project.snackcode.model.comment.CommentFormModel;
import com.project.snackcode.model.comment.CommentModel;
import com.project.snackcode.model.member.LoginContextHolder;
import com.project.snackcode.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * 특정 게시글의 모든 댓글 조회
     * @param postId
     * @return
     */
    @Transactional(readOnly = true)
    public List<CommentModel> selectCommentList(Long postId) {

        Map<Long, List<CommentModel>> commentMap = commentRepository.findAllByPostId(postId).stream().map(Comment::toModel).collect(Collectors.groupingBy(CommentModel::getId));

        if (commentMap.keySet().isEmpty()) {
            return new ArrayList<>();
        }

        // 답글 정렬
        commentMap.values().forEach(list -> {
            CommentModel model = list.get(0);
            if (model.getPrntId() != null) {
                commentMap.get(model.getPrntId()).get(0).addChildComment(model);
                //commentMap.remove(model.getPrntId());
            }
        });

        List<CommentModel> commentList = commentMap.values().stream()
                .filter(value -> value.get(0).getPrntId() == null)
                .map(value -> {
                    CommentModel model = value.get(0);
                    model.getChildComment().sort(Comparator.comparing(CommentModel::getRegDt));
                    return model;
                })
                .collect(Collectors.toList());

        commentList.sort(Comparator.comparing(CommentModel::getRegDt));

        return commentList;
    }

    /**
     * 코멘트 저장
     * @param commentFormModel
     */
    @Transactional
    public void saveComment(CommentFormModel commentFormModel) {
        Comment comment = commentFormModel.toEntity();
        commentRepository.save(comment);
    }

    /**
     * 코멘트 수정
     * @param commentFormModel
     */
    @Transactional
    public void updateComment(CommentFormModel commentFormModel) {
        Comment comment = commentRepository.findById(commentFormModel.getId()).orElseThrow();

        if (comment.getMember().getId() != LoginContextHolder.getLoginUser().getId()) {
            throw BasicErrorException.builder().msg("incorrect memId and comment.memId").build();
        }

        comment.update(commentFormModel.getCntns());
    }


    /**
     * 코멘트 삭제
     * @param commentId
     */
    @Transactional
    public void deleteComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if (comment.getMember().getId() != LoginContextHolder.getLoginUser().getId()) {
            throw BasicErrorException.builder().msg("incorrect memId and comment.memId").build();
        }

        commentRepository.deleteById(commentId);
    }


}
