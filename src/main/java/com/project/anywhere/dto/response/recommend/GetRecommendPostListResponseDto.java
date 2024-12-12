package com.project.anywhere.dto.response.recommend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.anywhere.common.object.RecommendPost;
import com.project.anywhere.dto.response.ResponseCode;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.ResponseMessage;
import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendFoodEntity;
import com.project.anywhere.entity.RecommendImageEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.entity.RecommendPostEntity;

import lombok.Getter;

@Getter
public class GetRecommendPostListResponseDto extends ResponseDto {

    private List<RecommendPost> recommendPosts;

    public GetRecommendPostListResponseDto(List<RecommendPostEntity> postEntities, List<RecommendAttractionEntity> attractions, List<RecommendFoodEntity> foods, List<RecommendMissionEntity> missions, List<List<RecommendImageEntity>> images) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendPosts = RecommendPost.getList(postEntities, attractions, foods, missions, images);
    }

    public static ResponseEntity<GetRecommendPostListResponseDto> success(List<RecommendPostEntity> postEntities, List<RecommendAttractionEntity> attractions, List<RecommendFoodEntity> foods, List<RecommendMissionEntity> missions, List<List<RecommendImageEntity>> images) {
        GetRecommendPostListResponseDto responseBody = new GetRecommendPostListResponseDto(postEntities, attractions, foods, missions, images);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
