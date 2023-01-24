package com.example.toyproject.web.dto.users.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Boolean success;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @Builder
    public UserResponseDto(Boolean success, String messaage, Long userId, String email) {
        this.success=success;
        this.message=messaage;
        this.userId = userId;
        this.email = email;
    }

}
