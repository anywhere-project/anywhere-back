package com.project.anywhere.dto.response;

public interface ResponseMessage {

    String SUCCESS = "Success.";
    String LIKE_CLICK = "Like click.";
    String LIKE_UNCLICK = "Like unclick.";
    String SCRAP_CLICK = "Scrap click.";
    String SCRAP_UNCLICK = "Scrap unclick.";

    String VALIDATION_FAIL = "Validation failed.";
    String DUPLICATED_USER_ID = "Duplicated user id.";
    String DUPLICATED_TEL_NUMBER = "Duplicated user tel number.";

    String NO_EXIST_USER_ID = "No exist user id.";
    String NO_EXIST_TEL_NUMBER = "No exist tel number";
    String NO_EXIST_USER_ID_AND_TEL_NUMBER = "No exist user id and tel number";

    String SIGN_IN_FAIL = "Sign in failed.";
    String AUTHENTICATION_FAIL = "Authentication failed.";
    String PASSWORD_MISMATCH = "Password mismatch.";
    String TEL_AUTH_FAIL = "Tel number authentication failed.";
    String NO_PERMISSION = "No permission.";

    String TOKEN_CREATE_FAIL = "Token creation failed.";
    String DATABASE_ERROR = "Database error.";

}
