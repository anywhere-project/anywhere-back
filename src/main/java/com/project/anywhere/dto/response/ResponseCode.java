package com.project.anywhere.dto.response;

public interface ResponseCode {
    
    String SUCCESS = "SU";
    
    String VALIDATION_FAILED = "VF";
    String DUPLICATED_USER_ID = "DI";
    String DUPLICATED_TEL_NUMBER = "DT";

    String NO_EXIST_USER_ID = "NI";
    String NO_EXIST_TEL_NUMBER = "NT";
    String NO_EXIST_RECOMMEND_POST = "NRC";
    String NO_EXIST_RECOMMEND_ATTRACTION = "NRA";
    String NO_EXIST_RECOMMEND_FOOD = "NRF";
    String NO_EXIST_RECOMMEND_MISSION = "NRM";

    String SIGN_IN_FAIL = "SF";
    String AUTHENTICATION_FAIL = "AF";

    String TEL_AUTH_FAIL = "TAF";
    String NO_PERMISSION = "NP";

    String MESSAGE_SEND_FAIL = "TF";
    String TOKEN_CREATE_FAIL = "TCF";
    String DATABASE_ERROR = "DBE";
    
}
