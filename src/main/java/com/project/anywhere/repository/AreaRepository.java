package com.project.anywhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.anywhere.entity.AreasEntity;

public interface AreaRepository extends JpaRepository<AreasEntity, Integer> {

}