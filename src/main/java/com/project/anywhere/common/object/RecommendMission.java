package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class RecommendMission {

    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private String recommendCategory;
    private List<RecommendMissionEntity> missions;
    private List<RecommendImageEntity> images;

    public RecommendMission(RecommendPostEntity postEntity, List<RecommendMissionEntity> missionEntities, List<RecommendImageEntity> imageEntities) {
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.recommendCategory = postEntity.getRecommendCategory();
        this.missions = missionEntities;
        this.images = imageEntities;
    }

    public static List<RecommendMission> getList(List<RecommendPostEntity> postEntities, List<RecommendMissionEntity> missionEntities, List<RecommendImageEntity> imageEntities) {
        List<RecommendMission> recommendMissions = new ArrayList<>();

        for (RecommendPostEntity postEntity : postEntities) {
            Integer recommendId = postEntity.getRecommendId();

            List<RecommendMissionEntity> filteredMissions = new ArrayList<>();
            for (RecommendMissionEntity mission : missionEntities) {
                if (mission.getRecommendId().equals(recommendId)) {
                    filteredMissions.add(mission);
                }
            }

            List<RecommendImageEntity> filteredImages = new ArrayList<>();
            for (RecommendImageEntity image : imageEntities) {
                if (image.getRecommendId().equals(recommendId)) {
                    filteredImages.add(image);
                }
            }

            RecommendMission recommendMission = new RecommendMission(postEntity, filteredMissions, filteredImages);
            recommendMissions.add(recommendMission);
        }

        return recommendMissions;
    }
}
