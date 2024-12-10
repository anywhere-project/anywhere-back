package com.project.anywhere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "areas")
@Entity(name = "areas")
public class AreasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer areaId;
    private String areaName;
}