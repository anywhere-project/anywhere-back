package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.anywhere.entity.ReviewCommentEntity;

public interface ReviewCommentRepository extends JpaRepository<ReviewCommentEntity, Integer> {

    ReviewCommentEntity findByReviewCommentId(Integer reviewCommentId);
    ReviewCommentEntity findTopByOrderByOrderNumberDesc();
    List<ReviewCommentEntity> findByReviewIdOrderByReviewCommentIdAsc(Integer reviewId);
    
}
