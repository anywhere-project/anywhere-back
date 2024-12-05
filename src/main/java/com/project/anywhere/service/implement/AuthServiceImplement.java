package com.project.anywhere.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.anywhere.common.util.CreateNumber;
import com.project.anywhere.dto.request.auth.IdCheckRequestDto;
import com.project.anywhere.dto.request.auth.SignUpRequestDto;
import com.project.anywhere.dto.request.auth.TelAuthCheckRequestDto;
import com.project.anywhere.dto.request.auth.TelAuthRequestDto;
import com.project.anywhere.dto.response.ResponseDto;
import com.project.anywhere.entity.TelAuthEntity;
import com.project.anywhere.entity.UsersEntity;
import com.project.anywhere.provider.SmsProvider;
import com.project.anywhere.repository.TelAuthRepository;
import com.project.anywhere.repository.UserRepository;
import com.project.anywhere.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final SmsProvider smsProvider;

    private final UserRepository userRepository;
    private final TelAuthRepository telAuthRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {

        String userId = dto.getUserId();

        try {

            boolean isExisted = userRepository.existsByUserId(userId);
            if (isExisted) return ResponseDto.duplicatedUserId();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto) {

        String telNumber = dto.getTelNumber();

        try {

            boolean isExisted = userRepository.existsByTelNumber(telNumber);
            if (isExisted) return ResponseDto.duplicatedTelNumber();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        String authNumber = new CreateNumber().generateAuthNumber();

        boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
        if (!isSendSuccess) return ResponseDto.messageSendFail();

        try {

            TelAuthEntity telAuthEntity = new TelAuthEntity(telNumber, authNumber);
            telAuthRepository.save(telAuthEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto) {

        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();
    
        try {

            boolean isMatched = telAuthRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatched) return ResponseDto.telAuthFail();
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        
        String userId = dto.getUserId();
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();
        String password = dto.getPassword();

        try {

            boolean isExistedUserId = userRepository.existsByUserId(userId);
            if (isExistedUserId) return ResponseDto.duplicatedUserId();

            boolean isExistedTelNumber = userRepository.existsByTelNumber(telNumber);
            if (isExistedTelNumber) return ResponseDto.duplicatedTelNumber();

            boolean isMatched = telAuthRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatched) return ResponseDto.telAuthFail();

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UsersEntity userEntity = new UsersEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
}
