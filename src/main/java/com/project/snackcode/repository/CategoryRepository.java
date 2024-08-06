package com.project.snackcode.repository;

import com.project.snackcode.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByMember_IdAndPrntCategory_IdOrderByIdDesc(Long memId, Long prntCid);
    List<Category> findAllByMember_Id(Long memId);
}
