package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendMissionEntity;

import lombok.Getter;

@Getter
public class RecommendMission {
    
    private Integer missionId;
    private String missionName;
    private String missionContent;

    private RecommendMission(RecommendMissionEntity missionEntity) {
        this.missionId = missionEntity.getMissionId();
        this.missionName = missionEntity.getMissionName();
        this.missionContent = missionEntity.getMissionContent();
    }

    public static List<RecommendMission> getList(List<RecommendMissionEntity> missionEntities) {
        List<RecommendMission> missions = new ArrayList<>();

        for (RecommendMissionEntity missionEntity: missionEntities) {
            RecommendMission mission = new RecommendMission(missionEntity);
            missions.add(mission);
        }

        return missions;
    }

}
