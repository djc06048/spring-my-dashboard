package com.example.toyproject.domain.posts;

import com.example.toyproject.domain.bulletin.Bulletin;
import com.example.toyproject.domain.comments.Comments;
import com.example.toyproject.domain.users.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name="posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length=100, nullable=false)
    private String title;

    @Column(length=500, columnDefinition = "TEXT", nullable = false)
    private String content;



    @ManyToOne
    @JoinColumn(name="author_id")
    private Users author;


    @ManyToOne
    @JoinColumn(name = "bulletin_id")
    private Bulletin bulletin;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Comments> comments;

    @Builder
    public Posts(Users author,Bulletin bulletin,String title, String content) {
        this.author = author;
        this.bulletin=bulletin;
        this.title = title;
        this.content = content;
        this.comments=new ArrayList<Comments>();
    }
}
