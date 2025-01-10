package com.project.anywhere.entity;

import com.project.anywhere.entity.pk.FoodLikePk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "food_like")
@Entity(name = "food_like")
@IdClass(FoodLikePk.class)
public class FoodLikeEntity {

    @Id
    private String userId;
    @Id
    private Integer foodId;
    
    public FoodLikeEntity(String userId, Integer foodId) {
        this.userId = userId;
        this.foodId = foodId;
    }
    
}
