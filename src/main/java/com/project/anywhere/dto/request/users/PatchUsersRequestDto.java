package com.project.anywhere.dto.request.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUsersRequestDto {

    private String nickname;
    private String profileImage;
    private String name;
    private String telNumber;
    
}
