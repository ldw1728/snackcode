package com.project.snackcode.repository;

import com.project.snackcode.entity.PostLocker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLockerRepository extends JpaRepository<PostLocker, Long> {
    PostLocker findByMemId(Long memId);
}
