package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendMissionEntity;

import lombok.Getter;

@Getter
public class RecommendMission {

    private Integer missionId;
    private Integer recommendId;
    private String missionName;
    private String missionContent;

    private RecommendMission(RecommendMissionEntity missionEntity) {
        this.missionId = missionEntity.getMissionId();
        this.recommendId = missionEntity.getRecommendId();
        this.missionName = missionEntity.getMissionName();
        this.missionContent = missionEntity.getMissionContent();
    }

    public static List<RecommendMission> getList(List<RecommendMissionEntity> missionEntities, Integer recommendId) {
        List<RecommendMission> recommendMissions = new ArrayList<>();
        for (RecommendMissionEntity missionEntity : missionEntities) {
            if (missionEntity.getRecommendId().equals(recommendId)) {
                RecommendMission recommendMission = new RecommendMission(missionEntity);
                recommendMissions.add(recommendMission);
            }
        }
        return recommendMissions;
    }
    
}
