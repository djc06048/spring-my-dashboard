package com.example.toyproject.web.dto.users.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class userJoinRequestDto {
    private String email;
    private String password;
    private String name;
    @Builder
    public userJoinRequestDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;

    }




}
