package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.MissionImageEntity;
import com.project.anywhere.entity.RecommendMissionEntity;

import lombok.Getter;

@Getter
public class RecommendMission {

    private Integer missionId;
    private Integer recommendId;
    private String missionName;
    private String missionContent;
    private List<String> images;

    public RecommendMission(RecommendMissionEntity missionEntity, List<String> images) {
        this.missionId = missionEntity.getMissionId();
        this.recommendId = missionEntity.getRecommendId();
        this.missionName = missionEntity.getMissionName();
        this.missionContent = missionEntity.getMissionContent();
        this.images = images;
    }

    public static List<RecommendMission> getList(List<RecommendMissionEntity> missionEntities, List<MissionImageEntity> imageEntities) {
        List<RecommendMission> recommendMissions = new ArrayList<>();

        for (RecommendMissionEntity missionEntity : missionEntities) {
            List<String> filteredImages = new ArrayList<>();

            for (MissionImageEntity imageEntity : imageEntities) {
                if (imageEntity.getMissionId().equals(missionEntity.getMissionId())) {
                    filteredImages.add(imageEntity.getImageUrl());
                }
            }

            RecommendMission recommendMission = new RecommendMission(missionEntity, filteredImages);
            recommendMissions.add(recommendMission);
        }

        return recommendMissions;
    }
}
