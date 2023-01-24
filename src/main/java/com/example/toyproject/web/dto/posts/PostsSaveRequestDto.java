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



    @Builder
    public PostsSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;

    }



    public Posts toEntity() {
        return Posts.builder().title(title).content(content).build();
    }
}
