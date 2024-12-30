package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.AttractionImageEntity;

@Repository
public interface AttractionImageRepository extends JpaRepository<AttractionImageEntity, Integer> {
    
    boolean existsByImageId(Integer imageId);
    List<AttractionImageEntity> findByAttractionId(Integer attractionId);
    void deleteByImageId(Integer imageId);

}
