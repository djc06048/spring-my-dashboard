package com.example.toyproject.config.auth.dto;


import com.example.toyproject.domain.user.User;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class SessionUser implements Serializable {
    static final long serialVersionUID = 1L;

    private Long userId;
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
