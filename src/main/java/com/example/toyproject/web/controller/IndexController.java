package com.example.toyproject.web.controller;

import com.example.toyproject.service.comments.CommentService;
import com.example.toyproject.service.posts.PostsService;
import com.example.toyproject.web.dto.comments.CommentResponseDto;
import com.example.toyproject.web.dto.posts.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    public final PostsService postsService;
    public final CommentService commentService;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{postId}")
    public String postsUpdate(@PathVariable Long postId, Model model) {
        PostsResponseDto dto = postsService.findByPostId(postId);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/posts/{postId}/comments")
    public String Comments(@PathVariable Long postId,Model model){
        model.addAttribute("comments",commentService.findAllByPostId(postId));
        return "comments";
    }
    @GetMapping("/comments/update/{commentId}")
    public String commentsUpdate(@PathVariable Long commentId, Model model) {
        CommentResponseDto dto = commentService.findByCommentId(commentId);
        model.addAttribute("comment", dto);

        return "comments-update";
    }

}
