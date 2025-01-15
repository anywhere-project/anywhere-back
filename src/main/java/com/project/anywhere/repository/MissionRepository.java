package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.anywhere.entity.MissionsEntity;

public interface MissionRepository extends JpaRepository<MissionsEntity, Integer> {

    MissionsEntity findByMissionId(Integer missionId);
    void deleteByMissionId(Integer missionId);
    
}
