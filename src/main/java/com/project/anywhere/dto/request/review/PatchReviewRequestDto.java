package com.project.anywhere.dto.request.review;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchReviewRequestDto {

    @NotBlank
    private String reviewContent;
    @NotBlank
    private String imageUrl;  
    private List<String> hashtags;
    
}
