package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.AttractionLikeEntity;
import com.project.anywhere.entity.pk.AttractionLikePk;

import jakarta.transaction.Transactional;

@Repository
public interface AttractionLikeRepository extends JpaRepository<AttractionLikeEntity, AttractionLikePk> {

    boolean existsByUserIdAndAttractionId(String userId, Integer attractionId);
    @Transactional
    void deleteByUserIdAndAttractionId(String userId, Integer attractionId);
    
    
}
