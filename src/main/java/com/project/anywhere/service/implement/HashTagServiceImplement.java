package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.hashtag.PostHashTagRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.repository.HashTagRepository;
import com.project.anywhere.repository.ReviewPostRespsitory;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.HashTagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashTagServiceImplement implements HashTagService {

    private final UserRepository userRepository;
    private final ReviewPostRespsitory postRespsitory;
    private final HashTagRepository hashTagRepository;
    
    @Override
    public ResponseEntity<ResponseDto> postHashTag(PostHashTagRequestDto dto, Integer reviewId, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postHashTag'");
    }

    @Override
    public ResponseEntity<ResponseDto> deleteHashTag(Integer reviewId, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteHashTag'");
    }
    

    
}
