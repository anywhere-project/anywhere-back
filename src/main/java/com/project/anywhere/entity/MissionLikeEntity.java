package com.project.anywhere.entity;

import com.project.anywhere.entity.pk.MissionLikePk;

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
@Table(name = "mission_like")
@Entity(name = "mission_like")
@IdClass(MissionLikePk.class)
public class MissionLikeEntity {

    @Id
    private String userId;
    
    @Id
    private Integer missionId;
    
    public MissionLikeEntity(String userId, Integer missionId) {
        this.userId = userId;
        this.missionId = missionId;
    }
    
}
