package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.HashTagEntity;
import com.project.anywhere.repository.resultset.GetHashTagResultSet;

@Repository
public interface HashTagRepository extends JpaRepository<HashTagEntity, Integer> {
    
    void deleteByTagId(Integer tagId);
    HashTagEntity findByReviewId(Integer reviewId);

    @Query(
        value = 
        "SELECT tag_name AS tagName, COUNT(*) AS usage_count " + 
        "FROM hash_tag " + 
        "GROUP BY tag_name " + 
        "ORDER BY usage_count DESC " + 
        "LIMIT 5",
    nativeQuery = true)
    List<GetHashTagResultSet> getHashTagList();

}
