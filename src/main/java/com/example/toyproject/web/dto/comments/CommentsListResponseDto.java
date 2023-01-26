package com.example.toyproject.web.dto.comments;

import com.example.toyproject.domain.comments.Comments;
import lombok.Getter;

@Getter
public class CommentsListResponseDto {
    private Long commentId;
    private Long postId;
    private String content;
    private Long userId;
    private String modifiedDate;

    public CommentsListResponseDto(Comments Entity) {
        this.commentId=Entity.getCommentId();
        this.postId = Entity.getPost().getPostId();
        this.content = Entity.getContent();
        this.userId = Entity.getUser().getUserId();
        this.modifiedDate = Entity.getLastModifiedAt();
    }
}
