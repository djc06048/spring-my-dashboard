package com.example.toyproject.web.dto.comments;

import com.example.toyproject.domain.comments.Comments;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Boolean success;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long postId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long commentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastModified;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comment;
    @Builder
    public CommentResponseDto(Boolean success, String message, Comments entity) {
        this.success = success;
        this.message = message;
        if(Objects.isNull(entity)){
            this.userId = null;
            this.postId = null;
            this.commentId = null;
            this.createAt = null;
            this.lastModified = null;
            this.comment = null;
        }else{
            this.userId = entity.getUser().getUserId();
            this.postId = entity.getPost().getPostId();
            this.commentId = entity.getCommentId();
            this.createAt = entity.getCreateAt();
            this.lastModified = entity.getLastModifiedAt();
            this.comment = entity.getContent();
        }

    }
}
