package com.project.anywhere.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;
    
    public static ResponseEntity<ResponseDto> success() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> likeClick() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.LIKE_CLICK, ResponseMessage.LIKE_CLICK);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> likeUnclick() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.LIKE_UNCLICK, ResponseMessage.LIKE_UNCLICK);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> scrapClick() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SCRAP_CLICK, ResponseMessage.SCRAP_CLICK);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> scrapUnclick() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SCRAP_UNCLICK, ResponseMessage.SCRAP_UNCLICK);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> validationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAILED, ResponseMessage.VALIDATION_FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> AuthenticationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.AUTHENTICATION_FAIL, ResponseMessage.AUTHENTICATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    
    public static ResponseEntity<ResponseDto> duplicatedUserId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_USER_ID, ResponseMessage.DUPLICATED_USER_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedTelNumber() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_TEL_NUMBER, ResponseMessage.DUPLICATED_TEL_NUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> alreadyRecommend() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.ALREADY_RECOMMEND, ResponseMessage.ALREADY_RECOMMEND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUserId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_USER_ID, ResponseMessage.NO_EXIST_USER_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistAreaId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_AREA_ID, ResponseMessage.NO_EXIST_AREA_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistAttractionId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_ATTRACTION_ID, ResponseMessage.NO_EXIST_ATTRACTION_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistFoodId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_FOOD_ID, ResponseMessage.NO_EXIST_FOOD_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistMissionId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_MISSION_ID, ResponseMessage.NO_EXIST_MISSION_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistRecommendPost() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_RECOMMEND_POST, ResponseMessage.NO_EXIST_RECOMMEND_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistRecommendAttraction() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_RECOMMEND_ATTRACTION, ResponseMessage.NO_EXIST_RECOMMEND_ATTRACTION);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistRecommendFood() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_RECOMMEND_FOOD, ResponseMessage.NO_EXIST_RECOMMEND_FOOD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistRecommendMission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_RECOMMEND_MISSION, ResponseMessage.NO_EXIST_RECOMMEND_MISSION);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistRecommendImage() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_RECOMMEND_IMAGE, ResponseMessage.NO_EXIST_RECOMMEND_IMAGE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistReviewPost() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_REVIEW_POST, ResponseMessage.NO_EXIST_REVIEW_POST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistReview(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_REVIEW, ResponseMessage.NO_EXIST_REVIEW);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistReviewComment(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_REVIEW_COMMENT, ResponseMessage.NO_EXIST_REVIEW_COMMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistParentComment(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_PARENT_COMMENT, ResponseMessage.NO_EXIST_PARENT_COMMENT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistMyRandom(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_MY_RANDOM, ResponseMessage.NO_EXIST_MY_RANDOM);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> telAuthFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.TEL_AUTH_FAIL, ResponseMessage.TEL_AUTH_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> messageSendFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.MESSAGE_SEND_FAIL, ResponseMessage.MESSAGE_SEND_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> tokenCreateFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.TOKEN_CREATE_FAIL, ResponseMessage.TOKEN_CREATE_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

}
