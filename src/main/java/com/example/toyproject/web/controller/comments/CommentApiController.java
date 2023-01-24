package com.example.toyproject.web.controller.comments;

import com.example.toyproject.service.comments.CommentService;
import com.example.toyproject.web.dto.comments.CommentResponseDto;
import com.example.toyproject.web.dto.comments.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping()
    public CommentResponseDto saveComment(@RequestBody CommentSaveRequestDto req){
        try{
            return commentService.saveComment(req.getPostId(),req.getContent(),req.getUserId());
        }catch(Exception e){
            return new CommentResponseDto(false,e.getMessage(),null);
        }
    }
}
