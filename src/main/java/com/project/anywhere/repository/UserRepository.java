package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {

    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber); 
    boolean existsByTelNumberAndUserId(String telNumber, String userId);
    UsersEntity findByUserId(String userId);

    @Query(value= 
    "SELECT * " +
    "FROM users " +
    "WHERE user_id IN ( " +
        "SELECT user_id FROM recommend_post " +
        "WHERE recommend_id = :recommendId" +
    ")",
    nativeQuery=true
    )
    List<UsersEntity> findUserByRecommendId(@Param("recommendId") Integer recommendId);
    
}