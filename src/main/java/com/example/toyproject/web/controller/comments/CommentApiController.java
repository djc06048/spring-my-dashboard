package com.example.toyproject.web.controller.comments;

import com.example.toyproject.service.comments.CommentService;
import com.example.toyproject.web.dto.comments.CommentResponseDto;
import com.example.toyproject.web.dto.comments.CommentSaveRequestDto;
import com.example.toyproject.web.dto.comments.CommentUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @Operation(summary = "특정 게시글에 댓글 등록", description = "로그인이 된 사용자의 경우 특정 게시글에 댓글 등록 성공, 로그인 되지 않은 사용자의 경우 댓글 등록 실패")

    @PostMapping("comments")
    public CommentResponseDto saveComment(@RequestBody CommentSaveRequestDto req) {
        try {
            return commentService.saveComment(req.getPostId(), req.getContent(), req.getUserId());
        } catch (Exception e) {
            return new CommentResponseDto(false, e.getMessage(), null);
        }
    }

    @Operation(summary = "특정 게시글의 댓글 수정", description = "해당 댓글을 작성한 사용자의 경우 댓글 수정 성공, 해당 댓글을 작성한 사용자가 아닌 경우 댓글 수정 실패 및, 로그인 되지 않은 유저인 경우에도 댓글 수정 실패")

    @PutMapping("comments/posts/{postId}/users/{userId}")
    public CommentResponseDto updateComment(@RequestBody CommentUpdateRequestDto req, @Parameter(name = "postId", description = "수정하려는 댓글의 id", in = ParameterIn.PATH) @PathVariable Long postId, @Parameter(name = "userId", description = "수정하려는 댓글을 작성한 사용자 id", in = ParameterIn.PATH) @PathVariable Long userId) {
        try {
            return commentService.updateComment(postId, req.getContent(), userId);
        } catch (Exception e) {
            return new CommentResponseDto(false, e.getMessage(), null);
        }
    }

    @Operation(summary = "특정 commentId로 댓글불러오기")

    @GetMapping("/comments/{commentId}")
    public CommentResponseDto findByCommentId(@Parameter(name = "commentId", description = "댓글 id", in = ParameterIn.PATH) @PathVariable Long commentId) {
        try {
            return commentService.findByCommentId(commentId);
        } catch (Exception e) {
            return new CommentResponseDto(false, e.getMessage(), null);
        }

    }
}
