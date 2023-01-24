package com.example.toyproject.web.controller.posts;

import com.example.toyproject.domain.users.Users;
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
    @PostMapping("posts/users/{userId}")
    public PostsResponseDto save(@RequestBody PostsSaveRequestDto requestDto,@PathVariable Long userId){
        try{
            return postsService.save(requestDto,userId);
        }catch(Exception e){
            PostsResponseDto res=new PostsResponseDto(false,e.getMessage(),null);
            return res;
        }
    }
    @PutMapping("/posts/{postId}/users/{userId}")
    public PostsResponseDto update(@PathVariable Long postId, @PathVariable Long userId,@RequestBody PostsUpdateRequestDto requestDto){
        try{
            return postsService.update(postId,userId,requestDto);
        }catch(Exception e){
            return new PostsResponseDto(false,e.getMessage(),null);
        }
    }

    @GetMapping("/posts/{postId}")
    public PostsResponseDto findById(@PathVariable Long postId){
        try{
            PostsResponseDto res=postsService.findById(postId);
            return res;
        }catch(Exception e){
            return new PostsResponseDto(false,e.getMessage(),null);
        }
    }




}
