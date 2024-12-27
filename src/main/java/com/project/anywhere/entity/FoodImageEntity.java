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
@Table(name = "food_images")
@Entity(name = "food_images")
public class FoodImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;
    private Integer foodId;
    private String imageUrl;

    public FoodImageEntity(String image, Integer foodId) {
        this.foodId = foodId;
        this.imageUrl = image;
    }
}
