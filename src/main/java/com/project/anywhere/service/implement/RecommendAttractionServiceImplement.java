package com.project.anywhere.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PatchRecommendAttractionRequestDto;
import com.project.anywhere.dto.request.recommend.PostRecommendAttractionRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.recommend.GetRecommendAttractionListResponseDto;
import com.project.anywhere.entity.AttractionImageEntity;
import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.entity.RecommendPostEntity;
import com.project.anywhere.repository.AttractionImageRepository;
import com.project.anywhere.repository.RecommendAttractionRepository;
import com.project.anywhere.repository.RecommendPostRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RecommendAttractionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendAttractionServiceImplement implements RecommendAttractionService {

    private final UserRepository userRepository;
    private final RecommendPostRepository postRepository;
    private final RecommendAttractionRepository attractionRepository;
    private final AttractionImageRepository imageRepository;
    
    @Override
    public ResponseEntity<ResponseDto> postRecommendAttraction(PostRecommendAttractionRequestDto dto, Integer recommendId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendAttractionEntity attractionEntity = new RecommendAttractionEntity(dto, recommendId);
            attractionRepository.save(attractionEntity);

            for (String image: dto.getImages()) {
                AttractionImageEntity imageEntity = new AttractionImageEntity(image, attractionEntity.getAttractionId());
                imageRepository.save(imageEntity);
            }

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchRecommendAttraction(PatchRecommendAttractionRequestDto dto, Integer recommendId, Integer attractionId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            boolean isExistedRecommendPost = postRepository.existsByRecommendId(recommendId);
            if (!isExistedRecommendPost) return ResponseDto.noExistRecommendPost();

            RecommendPostEntity postEntity = postRepository.findByRecommendId(recommendId);
            if (postEntity == null) return ResponseDto.noExistRecommendPost();

            if (!postEntity.getRecommendWriter().equals(userId)) return ResponseDto.noPermission();

            RecommendAttractionEntity attractionEntity = attractionRepository.findByAttractionId(attractionId);

            attractionEntity.patch(dto);
            attractionRepository.save(attractionEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteRecommendAttraction(Integer recommendId, Integer attractionId, String userId) {
        
        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendPostEntity postEntity = postRepository.findByRecommendId(recommendId);
            if (postEntity == null) return ResponseDto.noExistRecommendPost();
            if (!postEntity.getRecommendWriter().equals(userId)) return ResponseDto.noPermission(); 

            RecommendAttractionEntity attractionEntity = attractionRepository.findByAttractionId(attractionId);
            if (attractionEntity == null) return ResponseDto.noExistRecommendAttraction();

            attractionRepository.deleteByAttractionId(attractionId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetRecommendAttractionListResponseDto> getRecommendAttractionPosts(Integer recommendId) {
        List<RecommendAttractionEntity> attractionEntities = new ArrayList<>();
        List<AttractionImageEntity> imageEntities = new ArrayList<>();

        try {

            boolean isExistedRecommendPost = postRepository.existsByRecommendId(recommendId);
            if (!isExistedRecommendPost) return ResponseDto.noExistRecommendPost();

            attractionEntities = attractionRepository.findByRecommendId(recommendId);
            imageEntities = imageRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRecommendAttractionListResponseDto.success(attractionEntities, imageEntities);
    }
    

}
