package com.example.toyproject.domain.comments;


import com.example.toyproject.domain.BaseTimeEntity;
import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.users.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity

public class Comments extends BaseTimeEntity {
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;


    @ManyToOne(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Posts post;


    @Builder
    public Comments(String content, Users user, Posts post) {
        this.content = content;
        this.user = user;
        this.post = post;
    }
}
