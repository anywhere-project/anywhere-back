package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.AttractionLikeEntity;
import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.AttractionLikeRepository;
import com.project.anywhere.repository.RecommendAttractionRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.AttractionLikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionLikeServiceImplement implements AttractionLikeService {

    private final UserRepository userRepository;
    private final AttractionLikeRepository likeRepository;
    private final RecommendAttractionRepository attractionRepository;

    @Override
    public ResponseEntity<ResponseDto> attractionLike(Integer attractionId, String userId) {
        
        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if (usersEntity == null) return ResponseDto.noExistUserId();

            RecommendAttractionEntity attractionEntity = attractionRepository.findByAttractionId(attractionId);
            if (attractionEntity == null) return ResponseDto.noExistRecommendAttraction();

            boolean isLiked = likeRepository.existsByUserIdAndAttractionId(userId, attractionId);
            if (isLiked) {
                likeRepository.deleteByUserIdAndAttractionId(userId, attractionId);
                attractionEntity.setAttractionLike(attractionEntity.getAttractionLike() - 1);
                attractionRepository.save(attractionEntity);
                return ResponseDto.likeUnclick();
            } else {
                AttractionLikeEntity likeEntity = new AttractionLikeEntity(userId, attractionId);
                likeRepository.save(likeEntity);
                attractionEntity.setAttractionLike(attractionEntity.getAttractionLike() + 1);
                attractionRepository.save(attractionEntity);
                return ResponseDto.likeClick();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
    
}
