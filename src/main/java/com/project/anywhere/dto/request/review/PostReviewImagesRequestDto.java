package com.project.anywhere.dto.request.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReviewImagesRequestDto {

    @NotBlank
    private String imageUrl;
    @NotNull
    private Integer imageOrder;

}
