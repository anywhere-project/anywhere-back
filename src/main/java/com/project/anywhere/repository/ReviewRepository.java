package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewPostEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewPostEntity, Integer>{

    
    
}
