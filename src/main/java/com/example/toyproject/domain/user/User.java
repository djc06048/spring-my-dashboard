package com.example.toyproject.domain.user;

import com.example.toyproject.domain.BaseTimeEntity;
import com.example.toyproject.domain.comments.Comments;
import com.example.toyproject.domain.posts.Posts;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    @Column
    private String picture;
    @Column(name="name")
    private String name;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Posts> posts=new ArrayList<>();

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comments> comments=new ArrayList<>();


    @Builder
    public User(String name, String email, String picture, String password, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.password = password;
    }

    //소셜로그인파트
    public User update(String name, String picture){
        this.name=name;
        this.picture=picture;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }

}