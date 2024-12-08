package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewLikeEntity;
import com.project.anywhere.entity.pk.ReviewLikePk;

import jakarta.transaction.Transactional;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLikeEntity, ReviewLikePk> {
    
    boolean existsByUserIdAndReviewId(String userId, Integer reviewId);
    @Transactional
    void deleteByUserIdAndReviewId(String userId, Integer reviewId);
    
}
