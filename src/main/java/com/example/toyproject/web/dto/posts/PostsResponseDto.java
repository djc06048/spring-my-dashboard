package com.example.toyproject.web.dto.posts;

import com.example.toyproject.domain.posts.Posts;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;
import java.util.Optional;

@ToString
@Getter
@NoArgsConstructor
public class PostsResponseDto {

    private Boolean success;
    private String message;
    //FIXME: json 파싱시 널일때 값이 찍히나 안찍히나를 나타냄
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long postId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastModified;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createAt;

    @Builder
    public PostsResponseDto(Boolean success,String message,Posts entity){
        this.success=success;
        this.message=message;
        if(Objects.isNull(entity)){
            this.postId=null;
            this.content=null;
            this.title=null;
            this.userId=null;
            this.createAt=null;
            this.lastModified=null;
        }else{
            this.postId=entity.getPostId();
            this.content=entity.getContent();
            this.title=entity.getTitle();
            this.userId=entity.getUser().getUserId();
            this.createAt=entity.getCreateAt();
            this.lastModified=entity.getLastModifiedAt();
        }


    }
}
