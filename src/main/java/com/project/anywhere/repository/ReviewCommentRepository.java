package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.anywhere.entity.ReviewCommentEntity;

public interface ReviewCommentRepository extends JpaRepository<ReviewCommentEntity, Integer> {

    ReviewCommentEntity findByReviewCommentId(Integer reviewCommentId);
    ReviewCommentEntity findTopByOrderByOrderNumberDesc();
    
}
