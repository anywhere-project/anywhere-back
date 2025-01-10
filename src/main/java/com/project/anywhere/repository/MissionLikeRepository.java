package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.MissionLikeEntity;
import com.project.anywhere.entity.pk.MissionLikePk;

import jakarta.transaction.Transactional;

@Repository
public interface MissionLikeRepository extends JpaRepository<MissionLikeEntity, MissionLikePk> {

    boolean existsByUserIdAndMissionId(String userId, Integer missionId);
    @Transactional
    void deleteByUserIdAndMissionId(String userId, Integer missionId);
    
}
