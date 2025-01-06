package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.anywhere.dto.request.users.PatchUsersRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.users.GetUserInfoResponseDto;
import com.project.anywhere.dto.response.users.GetUsersResponseDto;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImplement implements UsersService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetUsersResponseDto> getUser(String userId) {

        UsersEntity usersEntity = null;

        try {
            usersEntity = userRepository.findByUserId(userId);
            if(usersEntity == null) return ResponseDto.noExistUserId();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUsersResponseDto.success(usersEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> patchUser(PatchUsersRequestDto dto, String userId) {
        try {

            UsersEntity usersEntity = userRepository.findByUserId(userId);
            if(usersEntity == null) return ResponseDto.noExistUserId();

            String patchUserId = usersEntity.getUserId();
            boolean isUser = patchUserId.equals(userId);
            if(!isUser) return ResponseDto.noPermission();

            if(dto.getTelNumber() != null && !dto.getTelNumber().isEmpty()){
                boolean isExistedTelNumber = userRepository.existsByTelNumber(dto.getTelNumber());
                boolean isMyTelNumber = userRepository.existsByTelNumberAndUserId(dto.getTelNumber(), userId);
                if(!isMyTelNumber && isExistedTelNumber) return ResponseDto.duplicatedTelNumber();
            }
            if(dto.getProfileImage() != null){
                usersEntity.setProfileImage(dto.getProfileImage());
            }
            if(dto.getName()!=null){
                usersEntity.setName(dto.getName());
            }
            if(dto.getTelNumber()!=null){
                usersEntity.setTelNumber(dto.getTelNumber());
            }
            if(dto.getNickname()!=null){
                usersEntity.setNickname(dto.getNickname());
            }

            userRepository.save(usersEntity);
            
            
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String userId) {

        UsersEntity usersEntity = null;

        try {

            usersEntity = userRepository.findByUserId(userId);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserInfoResponseDto.success(usersEntity);
    }


    
}
