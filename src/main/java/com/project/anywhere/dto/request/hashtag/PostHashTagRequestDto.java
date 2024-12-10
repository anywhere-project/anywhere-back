package com.project.anywhere.dto.request.hashtag;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostHashTagRequestDto {

    @NotBlank
    private String tagName;
    
}
