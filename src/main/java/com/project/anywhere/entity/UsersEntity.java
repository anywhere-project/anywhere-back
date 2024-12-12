package com.project.anywhere.entity;

import com.project.anywhere.dto.request.auth.SignUpRequestDto;
import com.project.anywhere.dto.request.users.PatchUsersRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class UsersEntity {    
    @Id
    private String userId;
    private String telNumber;
    private String password;
    private String name;
    private String nickname;
    private String profileImage = "https://blog.kakaocdn.net/dn/4CElL/btrQw18lZMc/Q0oOxqQNdL6kZp0iSKLbV1/img.png";
    private Boolean isAdmin = false;
    private String userStatus = "active";

    public UsersEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.telNumber = dto.getTelNumber();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.nickname = dto.getNickname();
    }

    public void patch(PatchUsersRequestDto dto){
        this.profileImage = dto.getProfileImage();
        this.nickname = dto.getNickname();
        this.name = dto.getName();
        this.telNumber = dto.getTelNumber();
    }
    
}
