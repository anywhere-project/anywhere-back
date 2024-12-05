package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.anywhere.entity.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {


    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber); 
    boolean existsByTelNumberAndUserId(String telNumber, String userId);

    UsersEntity findByUserId(String userId);

}

