package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendMissionPostResponseDto extends ResponseDto {
    
    private Integer recommendId;
    private String recommendCreatedAt;
    private String recommendWriter;
    private Integer recommendLikeCount;
    private String recommendCategory;
    private List<RecommendMissionEntity> missions;
    private List<RecommendImageEntity> images;

    public GetRecommendMissionPostResponseDto(RecommendPostEntity postEntity, List<RecommendMissionEntity> missionEntities, List<RecommendImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendId = postEntity.getRecommendId();
        this.recommendCreatedAt = postEntity.getRecommendCreatedAt();
        this.recommendWriter = postEntity.getRecommendWriter();
        this.recommendLikeCount = postEntity.getRecommendLikeCount();
        this.recommendCategory = postEntity.getRecommendCategory();
        this.missions = missionEntities;
        this.images = imageEntities;
    }

    public static ResponseEntity<GetRecommendMissionPostResponseDto> success(RecommendPostEntity postEntity, List<RecommendMissionEntity> missionEntities, List<RecommendImageEntity> imageEntities) {
        GetRecommendMissionPostResponseDto responseBody = new GetRecommendMissionPostResponseDto(postEntity, missionEntities, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


}
