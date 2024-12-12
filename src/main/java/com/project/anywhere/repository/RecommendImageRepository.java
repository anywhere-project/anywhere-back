package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.RecommendImageEntity;

@Repository
public interface RecommendImageRepository extends JpaRepository<RecommendImageEntity, Integer> {
    
    boolean existsByRecommendId(Integer recommendId);
    RecommendImageEntity findByRecommendId(Integer recommendId);
    RecommendImageEntity findByImageId(Integer imageId);
    List<RecommendImageEntity> findByRecommendIdOrderByImageOrderAsc(Integer recommendId);
    void deleteByImageId(Integer imageId);

}
