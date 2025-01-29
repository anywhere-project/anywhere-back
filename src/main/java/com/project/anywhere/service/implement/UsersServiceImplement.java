package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.anywhere.common.util.CreateNumber;
import com.project.anywhere.dto.request.auth.TelAuthCheckRequestDto;
import com.project.anywhere.dto.request.users.PatchPasswordRequestDto;
import com.project.anywhere.dto.request.users.PatchTelAuthRequestDto;
import com.project.anywhere.dto.request.users.PatchUsersRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.dto.response.users.GetUserInfoResponseDto;
import com.project.anywhere.dto.response.users.GetUsersResponseDto;
import com.project.anywhere.entity.TelAuthEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.provider.SmsProvider;
import com.project.anywhere.repository.TelAuthRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImplement implements UsersService{

    private final SmsProvider smsProvider;
    private final UserRepository userRepository;
    private final TelAuthRepository telAuthRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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


            // 비밀번호 변경 시 처리
            if (dto.getPassword() != null) {
                String encodedPassword = passwordEncoder.encode(dto.getPassword());
                usersEntity.setPassword(encodedPassword);
            }

            // 전화번호 변경 처리
            if (dto.getTelNumber() != null && !dto.getTelNumber().isEmpty()) {
                boolean isExistedTelNumber = userRepository.existsByTelNumber(dto.getTelNumber());
                boolean isMyTelNumber = userRepository.existsByTelNumberAndUserId(dto.getTelNumber(), userId);
                if (!isMyTelNumber && isExistedTelNumber) {
                    return ResponseDto.duplicatedTelNumber(); // 전화번호 중복
                }
                usersEntity.setTelNumber(dto.getTelNumber());
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

    @Override
    public ResponseEntity<ResponseDto> patchTelAuth(PatchTelAuthRequestDto dto, String userId) {

        String telNumber = dto.getTelNumber();
        String authNumber;

        try {

            UsersEntity user = userRepository.findByUserId(userId);
            if (user == null)
                return ResponseDto.noExistUserId();

            boolean isExisted = userRepository.existsByTelNumber(telNumber);
            if (isExisted)
                return ResponseDto.duplicatedTelNumber();

            authNumber = new CreateNumber().generateAuthNumber();
            boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
            if (!isSendSuccess)
                return ResponseDto.messageSendFail();

            TelAuthEntity telAuthEntity = new TelAuthEntity(telNumber, authNumber);
            telAuthEntity.patch(dto); 
            telAuthRepository.save(telAuthEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchTelAuthCheck(TelAuthCheckRequestDto dto, String userId) {

        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();

        try {

            UsersEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistUserId();

            boolean isMatched = telAuthRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatched) return ResponseDto.telAuthFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchPassword(PatchPasswordRequestDto dto, String userId) {

        String currentPassword = dto.getCurrentPassword();
        String newPassword = dto.getNewPassword();

        try {

            UsersEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistUserId();

            if (!passwordEncoder.matches(currentPassword, userEntity.getPassword())) {
                return ResponseDto.passwordMismatch();
            }

            String encodedPassword = passwordEncoder.encode(newPassword);
            userEntity.setPassword(encodedPassword);

            userRepository.save(userEntity);


        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }


    
}
