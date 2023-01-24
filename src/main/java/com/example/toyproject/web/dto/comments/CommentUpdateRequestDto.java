package com.example.toyproject.web.dto.comments;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private Long postId;
    private String content;
    private Long userId;

    @Builder
    public CommentUpdateRequestDto(Long postId, String content, Long userId) {
        this.postId = postId;
        this.content = content;
        this.userId = userId;
    }

}
