package com.example.toyproject.web.dto.users.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class userLoginRequestDto {
    private String email;
    private String password;

    @Builder
    public userLoginRequestDto(String email,String password){
        this.email=email;
        this.password=password;
    }

    @Override
    public String toString() {
        return "userLoginRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
