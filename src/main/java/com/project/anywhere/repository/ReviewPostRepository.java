package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewPostEntity;

@Repository
public interface ReviewPostRepository extends JpaRepository<ReviewPostEntity, Integer>{

    List<ReviewPostEntity> findByOrderByReviewIdDesc();
    ReviewPostEntity findByReviewId(Integer reviewId);
    boolean existsByReviewId(Integer reviewId);
    
}
