package com.project.anywhere.dto.request.recommend;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchRecommendImageRequestDto {
    
    @NotBlank
    private String imageUrl;
    @NotBlank
    private Integer imageOrder;

}
