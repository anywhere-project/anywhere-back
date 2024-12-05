package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.recommend.PostRecommendPostRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.RecommendPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendPostServiceImplement implements RecommendPostService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> postRecommendPost(PostRecommendPostRequestDto request, String userId) {

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (!isExistedUserId) return ResponseDto.noExistUserId();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
}
