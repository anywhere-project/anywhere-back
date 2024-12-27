package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PostMissionImageRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.MissionImageEntity;
import com.project.anywhere.entity.RecommendMissionEntity;
import com.project.anywhere.repository.MissionImageRepository;
import com.project.anywhere.repository.RecommendMissionRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.MissionImageService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissionImageServiceImplement implements MissionImageService {

    private final RecommendMissionRepository missionRepository;
    private final UserRepository userRepository;
    private final MissionImageRepository imageRepository;

    @Override
    public ResponseEntity<ResponseDto> postMissionImage(PostMissionImageRequestDto dto, Integer missionId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendMissionEntity missionEntity = missionRepository.findByMissionId(missionId);
            if (missionEntity == null) return ResponseDto.noExistRecommendMission();

            MissionImageEntity imageEntity = new MissionImageEntity(dto, missionId);
            imageRepository.save(imageEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> deleteMissionImage(Integer imageId, Integer missionId, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

            RecommendMissionEntity missionEntity = missionRepository.findByMissionId(missionId);
            if (missionEntity == null) return ResponseDto.noExistRecommendMission();

            boolean isExistedImage = imageRepository.existsByImageId(imageId);
            if (!isExistedImage) return ResponseDto.noExistRecommendMission();

            imageRepository.deleteByImageId(imageId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}
