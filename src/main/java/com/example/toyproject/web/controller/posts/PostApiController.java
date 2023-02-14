package com.example.toyproject.web.controller.posts;

import com.example.toyproject.service.posts.PostsService;

import com.example.toyproject.service.users.UserService;
import com.example.toyproject.web.dto.posts.PostsResponseDto;
import com.example.toyproject.web.dto.posts.PostsSaveRequestDto;
import com.example.toyproject.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostApiController {
    private final PostsService postsService;
    private final UserService userService;

    //title,content, userId를 body로 해서 post 저장
    @PostMapping(value = "posts", consumes = "application/json")
    public PostsResponseDto save(@RequestBody PostsSaveRequestDto requestDto) {
        try {
            return postsService.save(requestDto);
        } catch (Exception e) {
            PostsResponseDto res = new PostsResponseDto(false, e.getMessage(), null);
            System.out.println(res.getMessage());
            return res;
        }
    }

    //postId, userId pathVariable로 받고,
    //title과 content body로 요청받아 post 업데이트
    @PutMapping("/posts/{postId}/users/{userId}")
    public PostsResponseDto update(@PathVariable Long postId, @PathVariable Long userId, @RequestBody PostsUpdateRequestDto requestDto) {
        try {
            return postsService.update(postId, userId, requestDto);
        } catch (Exception e) {
            return new PostsResponseDto(false, e.getMessage(), null);
        }
    }

    //postId를 pathVariable로 받아서 해당 포스트 불러오기
    @GetMapping("/posts/{postId}")
    public PostsResponseDto findById(@PathVariable Long postId){
        try{
            PostsResponseDto res=postsService.findByPostId(postId);
            return res;
        }catch(Exception e){
            return new PostsResponseDto(false,e.getMessage(),null);
        }
    }




}
