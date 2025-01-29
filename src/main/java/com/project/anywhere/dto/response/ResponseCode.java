package com.project.anywhere.dto.response;

public interface ResponseCode {
    
    String SUCCESS = "SU";
    String LIKE_CLICK = "LC";
    String LIKE_UNCLICK = "LUC";
    String SCRAP_CLICK = "SC";
    String SCRAP_UNCLICK = "SUC";
    
    String VALIDATION_FAILED = "VF";
    String DUPLICATED_USER_ID = "DI";
    String DUPLICATED_TEL_NUMBER = "DT";
    String ALREADY_RECOMMEND = "AR";

    // 400 BAD REQUEST
    String NO_EXIST_USER_ID = "NI";
    String NO_EXIST_AREA_ID = "NA";
    String NO_EXIST_ATTRACTION_ID = "NAT";
    String NO_EXIST_FOOD_ID = "NAF";
    String NO_EXIST_MISSION_ID = "NAM";
    String NO_EXIST_TEL_NUMBER = "NT";
    String NO_EXIST_RECOMMEND_POST = "NRP";
    String NO_EXIST_RECOMMEND_ATTRACTION = "NRA";
    String NO_EXIST_RECOMMEND_FOOD = "NRF";
    String NO_EXIST_RECOMMEND_MISSION = "NRM";
    String NO_EXIST_RECOMMEND_IMAGE = "NRI";
    String NO_EXIST_REVIEW_POST = "NRV";
    String NO_EXIST_REVIEW_IMAGE = "NRWI";
    String NO_EXIST_REVIEW_HASHTAG = "NRWH";
    String NO_EXIST_REVIEW_COMMENT = "NRC";
    String NO_EXIST_PARENT_COMMENT = "NPC";
    String NO_EXIST_MY_RANDOM = "NMR";
    String PASSWORD_MISMATCH = "PM";

    String SIGN_IN_FAIL = "SF";
    String AUTHENTICATION_FAIL = "AF";

    String TEL_AUTH_FAIL = "TAF";
    String NO_PERMISSION = "NP";

    String MESSAGE_SEND_FAIL = "TF";
    String TOKEN_CREATE_FAIL = "TCF";
    String DATABASE_ERROR = "DBE";
    
}
