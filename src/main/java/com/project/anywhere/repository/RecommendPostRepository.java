package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.RecommendPostEntity;

@Repository
public interface RecommendPostRepository extends JpaRepository<RecommendPostEntity, Integer> {
    
}
