package com.example.toyproject.domain.comments;


import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.users.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users author;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post;


    @Builder
    public Comments(String content, Users author, Posts post) {
        this.content = content;
        this.author = author;
        this.post = post;
    }
}
