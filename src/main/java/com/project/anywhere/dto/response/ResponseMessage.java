package com.project.anywhere.dto.response;

public interface ResponseMessage {
    
    String SUCCESS = "Success.";
    
    String VALIDATION_FAILED = "Validation failed";
    String DUPLICATED_USER_ID = "Duplicated user id.";
    String DUPLICATED_TEL_NUMBER = "Duplicated user tel number.";

    String NO_EXIST_USER_ID = "No exist user id.";
    String NO_EXIST_TEL_NUMBER = "No exist tel number.";
    String SIGN_IN_FAIL = "Sign in fail.";
    String AUTHENTICATION_FAIL = "Authentication fail.";

    String TEL_AUTH_FAIL = "Tel auth fail.";

    String NO_PERMISSION = "No permission.";

    String MESSAGE_SEND_FAIL = "Message send fail.";
    String TOKEN_CREATE_FAIL = "Token create fail.";

    String DATABASE_ERROR = "Database error.";

}
