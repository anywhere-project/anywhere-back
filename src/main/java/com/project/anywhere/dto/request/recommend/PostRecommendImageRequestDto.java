package com.project.anywhere.dto.request.recommend;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRecommendImageRequestDto {
    
    @NotBlank
    private String imageUrl;
    @NotNull
    private Integer imageOrder;

}
