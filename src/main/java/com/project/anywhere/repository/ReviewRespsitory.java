package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.ReviewEntity;

@Repository
public interface ReviewRespsitory extends JpaRepository<ReviewEntity, Integer>{

    
    
}
