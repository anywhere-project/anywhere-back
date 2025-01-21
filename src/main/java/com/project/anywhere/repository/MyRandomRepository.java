package com.project.anywhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.anywhere.entity.MyRandomEntity;

@Repository
public interface MyRandomRepository extends JpaRepository<MyRandomEntity, Integer>  {

    MyRandomEntity findByRandomId(Integer randomId);
    List<MyRandomEntity> findByOrderByRandomIdDesc();
    void deleteByRandomId(Integer randomId);
    
}
