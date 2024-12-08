package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewPostEntity;

@Repository
public interface ReviewPostRespsitory extends JpaRepository<ReviewPostEntity, Integer> {

    ReviewPostEntity findByReviewId(Integer reviewId);

}
