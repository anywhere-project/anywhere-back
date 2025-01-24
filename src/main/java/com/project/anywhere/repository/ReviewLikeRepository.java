package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewLikeEntity;
import com.project.anywhere.entity.pk.ReviewLikePk;

import jakarta.transaction.Transactional;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLikeEntity, ReviewLikePk> {
    
    boolean existsByUserIdAndReviewId(String userId, Integer reviewId);
    @Transactional
    void deleteByUserIdAndReviewId(String userId, Integer reviewId);
    @Query(value = "SELECT user_id FROM review_likes WHERE review_id = :reviewId", nativeQuery = true)
    List<String> getReviewLikes(@Param("reviewId") Integer reviewId);

}
