package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewImagesEntity;

@Repository
public interface ReviewImagesRepository extends JpaRepository<ReviewImagesEntity, Integer>{

    List<ReviewImagesEntity> findByOrderByReviewIdDesc();

    ReviewImagesEntity findByReviewId(Integer reviewId);

    @Query("SELECT COALESCE(MAX(r.imageOrder), 0) FROM review_images r WHERE r.reviewId = :reviewId")
    Integer findMaxImageOrderByReviewId(@Param("reviewId") Integer reviewId);

}
