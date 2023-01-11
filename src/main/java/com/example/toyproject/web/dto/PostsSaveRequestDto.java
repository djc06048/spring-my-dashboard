package com.example.toyproject.web.dto;



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
    private Users author;




    @Builder
    public PostsSaveRequestDto(String title, String content, Users author) {
        this.title = title;
        this.content = content;
        this.author = author;

    }



    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
