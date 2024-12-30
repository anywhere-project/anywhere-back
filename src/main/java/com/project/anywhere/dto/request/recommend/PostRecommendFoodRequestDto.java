package com.project.anywhere.dto.request.recommend;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRecommendFoodRequestDto {

    @NotBlank
    private String foodName;
    @NotBlank
    private String foodContent;
    @NotEmpty
    private List<String> images;

}
