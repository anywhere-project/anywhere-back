package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.HashTagEntity;

@Repository
public interface HashTagRepository extends JpaRepository<HashTagEntity, Integer> {
    
    void deleteByTagId(Integer tagId);
    HashTagEntity findByReviewId(Integer reviewId);

}
