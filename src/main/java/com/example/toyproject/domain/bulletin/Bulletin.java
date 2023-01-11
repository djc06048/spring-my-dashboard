package com.example.toyproject.domain.bulletin;

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
@Table(name = "bulletin")
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bulletinId;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "bulletin",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Posts> posts;

    @Builder
    public Bulletin(String title) {
        this.title = title;
        this.posts = new ArrayList<Posts>();
    }
}