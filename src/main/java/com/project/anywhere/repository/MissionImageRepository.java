package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.MissionImageEntity;

@Repository
public interface MissionImageRepository extends JpaRepository<MissionImageEntity, Integer> {
    
    void deleteByImageId(Integer imageId);
    boolean existsByImageId(Integer imageId);

}
