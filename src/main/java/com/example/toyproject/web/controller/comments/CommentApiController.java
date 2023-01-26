package com.example.toyproject.web.controller.comments;

import com.example.toyproject.service.comments.CommentService;
import com.example.toyproject.web.dto.comments.CommentResponseDto;
import com.example.toyproject.web.dto.comments.CommentSaveRequestDto;
import com.example.toyproject.web.dto.comments.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("comments")
    public CommentResponseDto saveComment(@RequestBody CommentSaveRequestDto req){
        try{
            return commentService.saveComment(req.getPostId(),req.getContent(),req.getUserId());
        }catch(Exception e){
            return new CommentResponseDto(false,e.getMessage(),null);
        }
    }
    @PutMapping("comments/posts/{postId}/users/{userId}")
    public CommentResponseDto updateComment(@RequestBody CommentUpdateRequestDto req,@PathVariable Long postId,@PathVariable Long userId){
        try{
            return commentService.updateComment(postId,req.getContent(),userId);
        }catch(Exception e){
            return new CommentResponseDto(false,e.getMessage(),null);
        }
    }

    @GetMapping("/comments/{commentId}")
    public CommentResponseDto findByCommentId(@PathVariable Long commentId){
        try{
            return commentService.findByCommentId(commentId);
        }catch(Exception e){
            return new CommentResponseDto(false, e.getMessage(), null);
        }

    }
}
