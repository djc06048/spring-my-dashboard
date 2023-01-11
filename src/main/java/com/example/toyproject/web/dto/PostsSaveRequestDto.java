package com.example.toyproject.web.dto;


import com.example.toyproject.domain.bulletin.Bulletin;
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
    private Bulletin bulletin;

    private Integer readCnt;
    private Integer likeCnt;

    @Builder
    public PostsSaveRequestDto(String title, String content, Users author,Bulletin bulletin, Integer readCnt, Integer likeCnt) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.bulletin=bulletin;
        this.readCnt = readCnt;
        this.likeCnt = likeCnt;
    }



    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).readCnt(readCnt).likeCnt(likeCnt).build();
    }
}
