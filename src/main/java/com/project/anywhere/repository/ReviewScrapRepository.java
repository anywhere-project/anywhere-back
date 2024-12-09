package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewScrapEntity;
import com.project.anywhere.entity.pk.ReviewScrapPk;

import jakarta.transaction.Transactional;

@Repository
public interface ReviewScrapRepository extends JpaRepository<ReviewScrapEntity, ReviewScrapPk> {
    
    boolean existsByUserIdAndReviewId(String userId, Integer reviewId);
    @Transactional
    void deleteByUserIdAndReviewId(String userId, Integer reviewId);

}
