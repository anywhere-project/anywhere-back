package com.project.anywhere.dto.response;

public interface ResponseMessage {
    
    String SUCCESS = "Success.";
    String LIKE_CLICK = "Like click.";
    String LIKE_UNCLICK = "Like unclick.";
    String SCRAP_CLICK = "Scrap click.";
    String SCRAP_UNCLICK = "Scrap unclick.";
    
    String VALIDATION_FAILED = "Validation failed";
    String DUPLICATED_USER_ID = "Duplicated user id.";
    String DUPLICATED_TEL_NUMBER = "Duplicated user tel number.";
    String ALREADY_RECOMMEND = "Already recommended.";
    String NO_EXIST_REVIEW = "No exist Review.";

    String NO_EXIST_USER_ID = "No exist user id.";
    String NO_EXIST_AREA_ID = "No exist area id.";
    String NO_EXIST_ATTRACTION_ID = "No exist attraction id.";
    String NO_EXIST_FOOD_ID = "No exist food id.";
    String NO_EXIST_MISSION_ID = "No exist mission id.";
    String NO_EXIST_TEL_NUMBER = "No exist tel number.";
    String NO_EXIST_RECOMMEND_POST = "No exist recommend post.";
    String NO_EXIST_RECOMMEND_ATTRACTION = "No exist recommend attraction.";
    String NO_EXIST_RECOMMEND_FOOD = "No exist recommend food.";
    String NO_EXIST_RECOMMEND_MISSION = "No exist recommend mission.";
    String NO_EXIST_RECOMMEND_IMAGE = "No exist recommend image.";
    String NO_EXIST_REVIEW_POST = "No exist review post.";
    String NO_EXIST_REVIEW_COMMENT = "No exist review comment.";
    String NO_EXIST_PARENT_COMMENT = "No exist parent comment.";
    String NO_EXIST_MY_RANDOM = "No exist my random.";

    String SIGN_IN_FAIL = "Sign in fail.";
    String AUTHENTICATION_FAIL = "Authentication fail.";

    String TEL_AUTH_FAIL = "Tel auth fail.";

    String NO_PERMISSION = "No permission.";

    String MESSAGE_SEND_FAIL = "Message send fail.";
    String TOKEN_CREATE_FAIL = "Token create fail.";

    String DATABASE_ERROR = "Database error.";

}

