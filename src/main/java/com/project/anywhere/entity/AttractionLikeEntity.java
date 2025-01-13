package com.project.anywhere.entity;

import com.project.anywhere.entity.pk.AttractionLikePk;

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
@Table(name = "attraction_like")
@Entity(name = "attraction_like")
@IdClass(AttractionLikePk.class)
public class AttractionLikeEntity {

    @Id
    private String userId;
    @Id
    private Integer attractionId;
    
    public AttractionLikeEntity(String userId, Integer attractionId) {
        this.userId = userId;
        this.attractionId = attractionId;
    }
    
}
