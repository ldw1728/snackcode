package com.project.snackcode.repository;

import com.project.snackcode.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCategory_Id(Long cateId);
    Page<Post> findAllByCategory_IdOrderByIdDesc(Long cateId, Pageable pageable);
}
