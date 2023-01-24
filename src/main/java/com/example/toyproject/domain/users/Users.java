package com.example.toyproject.domain.users;

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

public class Users {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name="name")
    private String name;

//    @Enumerated(value=EnumType.STRING)
//    private UserRole userRole;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Posts> posts=new ArrayList<>();

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comments> comments=new ArrayList<>();

    @Builder
    public Users(String email,String name,String password) {
        this.email = email;
        this.name=name;
        this.password = password;

    }
    @Builder
    public Users(String email,String password) {
        this.email = email;
        this.password = password;

    }

    public void writePost(Posts savedPost) {
        this.posts.add(savedPost);
        savedPost.createdByUser(this);
    }

    public void writeComment(Comments savedComment) {
        this.comments.add(savedComment);
        savedComment.writeUser(this);
    }
}