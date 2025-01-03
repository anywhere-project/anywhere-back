package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.RecommendPostEntity;

@Repository
public interface RecommendPostRepository extends JpaRepository<RecommendPostEntity, Integer> {
    
    boolean existsByRecommendId(Integer recommendId);
    RecommendPostEntity findByRecommendId(Integer recommendId);
    void deleteByRecommendId(Integer recommendId);
    List<RecommendPostEntity> findAllByOrderByRecommendIdDesc();
    List<RecommendPostEntity> findByRecommendCategory(String recommendCategory);

}
