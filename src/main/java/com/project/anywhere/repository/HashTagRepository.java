package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.HashTagEntity;
import com.project.anywhere.repository.resultset.GetHashTagResultSet;

import jakarta.transaction.Transactional;

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

    @Query(value = "SELECT tag_name FROM hash_tag WHERE review_id = :reviewId", nativeQuery = true)
    List<String> getHashTags(@Param("reviewId") Integer reviewId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM hash_tag WHERE review_id = :reviewId", nativeQuery = true)
    void deleteByReviewId(@Param("reviewId") Integer reviewId);

}
