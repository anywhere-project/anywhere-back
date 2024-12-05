package com.project.anywhere.dto.request.review;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReviewRequestDto {
    @NotBlank
    private Integer reviewId;
    @NotBlank
    private String reviewContent;
    @NotBlank
    private String reviewWriter;
    @NotBlank
    private String reviewCreatedAt;
    @NotBlank
    @Min(0)
    private Integer reviewLikeCount;
    
}
