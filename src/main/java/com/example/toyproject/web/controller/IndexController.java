package com.example.toyproject.web.controller;

import com.example.toyproject.config.auth.LoginUser;
import com.example.toyproject.config.auth.dto.SessionUser;
import com.example.toyproject.service.comments.CommentService;
import com.example.toyproject.service.posts.PostsService;
import com.example.toyproject.web.dto.comments.CommentResponseDto;
import com.example.toyproject.web.dto.posts.PostsResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final CommentService commentService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(Model model,@LoginUser SessionUser user)
    {
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("userPicture", user.getPicture());
        }
        return "posts-save";
    }
    @GetMapping("/posts/{postId}/comments/save")
    public String commentsSave(@PathVariable Long postId, Model model,@LoginUser SessionUser user)
    {
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("userPicture", user.getPicture());
        }
        return "comments-save";
    }


    @GetMapping("/posts/update/{postId}")
    public String postsUpdate(@PathVariable Long postId, Model model,@LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findByPostId(postId);
        if (user != null) {
            model.addAttribute("userPicture", user.getPicture());
        }
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/posts/{postId}/comments")
    public String Comments(@PathVariable Long postId,Model model,@LoginUser SessionUser user){
        if (user != null) {
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("postId", postId);
        }
        model.addAttribute("comments",commentService.findAllByPostId(postId));
        return "comments";
    }
    //TODO: 다른 유저로 로그인 시, 수정기능이 막혀야됨. CommentsResponseDto의 success 메시지 활용해 경고창 띄워주기
    @GetMapping("/comments/update/{commentId}")
    public String commentsUpdate(@PathVariable Long commentId, Model model,@LoginUser SessionUser user) {
        CommentResponseDto dto = commentService.findByCommentId(commentId);
        if (user != null) {
            model.addAttribute("userPicture", user.getPicture());
        }
        model.addAttribute("comment", dto);

        return "comments-update";
    }


}
