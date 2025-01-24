package com.project.anywhere.dto.request.review;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchReviewRequestDto {

    @NotBlank
    private String reviewContent;
    @NotEmpty
    private List<String> images;
    private List<String> hashtags;
    
}
