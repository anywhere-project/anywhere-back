package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewPostEntity;
import com.project.anywhere.repository.resultset.GetReviewResultSet;

@Repository
public interface ReviewPostRepository extends JpaRepository<ReviewPostEntity, Integer>{

    List<ReviewPostEntity> findByOrderByReviewIdDesc();
    ReviewPostEntity findByReviewId(Integer reviewId);
    boolean existsByReviewId(Integer reviewId);

    @Query(value = "SELECT * FROM review_posts WHERE review_id = :reviewId", nativeQuery = true)
    GetReviewResultSet getReview(@Param("reviewId") Integer reviewId);
    
}
