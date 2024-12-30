package com.project.anywhere.dto.request.recommend;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostAttractionImageRequestDto {
    
    @NotBlank
    private String imageUrl;

}
