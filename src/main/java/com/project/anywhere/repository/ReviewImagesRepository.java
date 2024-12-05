package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewImagesEntity;

@Repository
public interface ReviewImagesRepository extends JpaRepository<ReviewImagesEntity, Integer>{

    
    
}
