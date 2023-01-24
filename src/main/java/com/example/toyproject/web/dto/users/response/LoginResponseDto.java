package com.example.toyproject.web.dto.users.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class LoginResponseDto {
    private Boolean success;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;

    @Builder
    public LoginResponseDto(Boolean success,String message,Long userId){
        this.success=success;
        this.message=message;
        this.userId=userId;

    }


}
