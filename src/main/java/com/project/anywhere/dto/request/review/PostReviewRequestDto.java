package com.project.anywhere.dto.request.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReviewRequestDto {
    @NotBlank
    private String reviewContent;
    @NotBlank
    private String imageUrl;  
    
}
