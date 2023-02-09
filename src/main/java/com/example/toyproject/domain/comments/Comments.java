package com.example.toyproject.domain.comments;


import com.example.toyproject.domain.BaseTimeEntity;
import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.user.User;
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

    @Column(name = "comment_content")
    private String content;

    @Column(name="comment_writer")
    private String writer;

    @ManyToOne(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Posts post;


    @Builder
    public Comments(String writer,String content) {
        this.writer=writer;
        this.content = content;

    }

    public void writtenPost(Posts post) {
        this.post=post;
        post.getComments().add(this);
    }


    public void writeUser(User user) {
        this.user=user;
    }

    public void update(String content) {
        this.content=content;
    }
}
