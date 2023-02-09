package com.example.toyproject.web.dto.posts;

import com.example.toyproject.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long postId;
    private String content;
    private String title;
    private Long userId;
    private String writer;
    private String modifiedDate;

    public PostsListResponseDto(Posts Entity) {
        this.postId = Entity.getPostId();
        this.content = Entity.getContent();
        this.userId=Entity.getUser().getUserId();
        this.title = Entity.getTitle();
        this.writer = Entity.getWriter();
        this.modifiedDate=Entity.getLastModifiedAt();
    }
}
