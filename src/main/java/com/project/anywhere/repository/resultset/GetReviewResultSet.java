package com.project.anywhere.repository.resultset;

public interface GetReviewResultSet {
    Integer getReviewId();
    String getReviewContent();
    String getReviewWriter();
    String getReviewCreatedAt();
    Integer getReviewLikeCount();
}
