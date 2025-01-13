package com.project.anywhere.entity.pk;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionLikePk implements Serializable {

    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "mission_id")
    private Integer missionId;
    
}
