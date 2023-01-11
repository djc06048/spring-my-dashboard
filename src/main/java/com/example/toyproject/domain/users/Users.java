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
@Table(name = "user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Posts> posts;

    @OneToMany(mappedBy="author",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comments> comments;

    @Builder
    public Users(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.posts = new ArrayList<Posts>();
        this.comments = new ArrayList<Comments>();
    }
}