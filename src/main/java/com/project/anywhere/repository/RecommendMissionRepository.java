package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.RecommendMissionEntity;

@Repository
public interface RecommendMissionRepository extends JpaRepository<RecommendMissionEntity, Integer> {

    boolean existsByRecommendId(Integer recommendId);
    RecommendMissionEntity findByMissionId(Integer missionId);
    void deleteByMissionId(Integer missionId);
    
}
