package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PostAttractionImageRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.AttractionImageEntity;
import com.project.anywhere.entity.RecommendAttractionEntity;
import com.project.anywhere.repository.AttractionImageRepository;
import com.project.anywhere.repository.RecommendAttractionRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.AttractionImageService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionImageServiceImplement implements AttractionImageService {

    private final RecommendAttractionRepository attractionRepository;
    private final UserRepository userRepository;
    private final AttractionImageRepository imageRepository;
    
    @Override
    public ResponseEntity<ResponseDto> postAttractionImage(PostAttractionImageRequestDto dto, Integer attractionId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendAttractionEntity attractionEntity = attractionRepository.findByAttractionId(attractionId);
            if (attractionEntity == null) return ResponseDto.noExistAttractionId();

            AttractionImageEntity imageEntity = new AttractionImageEntity(dto.getImageUrl(), attractionId);
            imageRepository.save(imageEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteAttractionImage(Integer imageId, Integer attractionId, String userId) {
        
        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendAttractionEntity attractionEntity = attractionRepository.findByAttractionId(attractionId);
            if (attractionEntity == null) return ResponseDto.noExistAttractionId();

            boolean isExistedImage = imageRepository.existsByImageId(imageId);
            if (!isExistedImage) return ResponseDto.noExistRecommendImage();

            imageRepository.deleteByImageId(imageId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
}
