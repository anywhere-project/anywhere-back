package com.project.anywhere.dto.request.reviewImages;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReviewImagesRequestDto {
    @NotBlank
    private Integer reviewId;
    @NotBlank
    private Integer imageId;
    @NotBlank
    private String imageUrl;
    @NotBlank
    private Integer imageOrder;
    
}
