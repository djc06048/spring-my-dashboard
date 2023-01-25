package com.example.toyproject.web.dto.posts;



import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private Long userId;


    @Builder
    public PostsSaveRequestDto(String title, String content,Long userId) {
        this.title = title;
        this.content = content;
        this.userId=userId;
    }



//    public Posts toEntity() {
//        return Posts.builder().title(title).content(content).build();
//    }
}
