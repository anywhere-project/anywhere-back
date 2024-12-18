package com.project.anywhere.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.anywhere.entity.RecommendImageEntity;

import lombok.Getter;

@Getter
public class RecommendImage {
    
    private Integer imageId;
    private Integer recommendId;
    private String imageUrl;
    private Integer imageOrder;

    private RecommendImage(RecommendImageEntity imageEntity) {
        this.imageId = imageEntity.getImageId();
        this.recommendId = imageEntity.getRecommendId();
        this.imageUrl = imageEntity.getImageUrl();
        this.imageOrder = imageEntity.getImageOrder();
    }

    public static List<RecommendImage> getList(List<RecommendImageEntity> imageEntities) {
        List<RecommendImage> images = new ArrayList<>();

        for (RecommendImageEntity imageEntity: imageEntities) {
            RecommendImage image = new RecommendImage(imageEntity);
            images.add(image);
        }

        return images;
    }

}
