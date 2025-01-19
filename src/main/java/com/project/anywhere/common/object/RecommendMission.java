package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.MissionImageEntity;
import com.project.anywhere.entity.MissionLikeEntity;
import com.project.anywhere.entity.RecommendMissionEntity;

import lombok.Getter;

@Getter
public class RecommendMission {

    private Integer missionId;
    private Integer recommendId;
    private String missionName;
    private String missionAddress;
    private String missionContent;
    private List<MissionImageEntity> images;
    private List<String> likeList;

    public RecommendMission(RecommendMissionEntity missionEntity, List<MissionImageEntity> images, List<String> likeList) {
        this.missionId = missionEntity.getMissionId();
        this.recommendId = missionEntity.getRecommendId();
        this.missionName = missionEntity.getMissionName();
        this.missionContent = missionEntity.getMissionContent();
        this.images = images;
        this.likeList = likeList;
    }

    public static List<RecommendMission> getList(List<RecommendMissionEntity> missionEntities, List<MissionImageEntity> missionImageEntities, List<MissionLikeEntity> missionLikeEntities) {
        List<RecommendMission> recommendMissions = new ArrayList<>();

        for (RecommendMissionEntity missionEntity : missionEntities) {
            List<MissionImageEntity> filteredImages = new ArrayList<>();

            for (MissionImageEntity missionImageEntity : missionImageEntities) {
                if (missionImageEntity.getMissionId().equals(missionEntity.getMissionId())) {
                    filteredImages.add(missionImageEntity);
                }
            }

            List<String> filteredUserList = new ArrayList<>();
            for (MissionLikeEntity missionLikeEntity : missionLikeEntities) {
                if (missionLikeEntity.getMissionId().equals(missionEntity.getMissionId())) {
                    filteredUserList.add(missionLikeEntity.getUserId());
                }
            }

            RecommendMission recommendMission = new RecommendMission(missionEntity, filteredImages, filteredUserList);
            recommendMissions.add(recommendMission);
        }

        return recommendMissions;
    }
}
