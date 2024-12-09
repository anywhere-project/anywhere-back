package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.RecommendLikeEntity;
import com.project.anywhere.entity.pk.RecommendLikePk;

import jakarta.transaction.Transactional;

@Repository
public interface RecommendLikeRepository extends JpaRepository<RecommendLikeEntity, RecommendLikePk> {
    
    boolean existsByUserIdAndRecommendId(String userId, Integer recommendId);
    @Transactional
    void deleteByUserIdAndRecommendId(String userId, Integer recommendId);

}
