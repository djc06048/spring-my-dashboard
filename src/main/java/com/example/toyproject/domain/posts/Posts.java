package com.example.toyproject.domain.posts;


import com.example.toyproject.domain.BaseTimeEntity;
import com.example.toyproject.domain.comments.Comments;
import com.example.toyproject.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length=100, nullable=false)
    private String title;

    @Column(length=500, columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @Column(length=100,nullable=false)
    private String writer;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comments> comments=new ArrayList<>();

    @Builder
    public Posts(String writer, String title, String content, User user) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
