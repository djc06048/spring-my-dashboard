package com.example.toyproject.web.controller;

import com.example.toyproject.config.auth.LoginUser;
import com.example.toyproject.config.auth.dto.SessionUser;
import com.example.toyproject.domain.user.User;
import com.example.toyproject.domain.user.UserRepository;
import com.example.toyproject.service.comments.CommentService;
import com.example.toyproject.service.posts.PostsService;
import com.example.toyproject.web.dto.comments.CommentResponseDto;
import com.example.toyproject.web.dto.comments.CommentUpdateRequestDto;
import com.example.toyproject.web.dto.posts.PostsResponseDto;
import com.example.toyproject.web.utils.WrongUserExceptions;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
@Slf4j
public class IndexController {
    private final PostsService postsService;
    private final CommentService commentService;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        //등록된 모든 글 보여주기(홈화면)
        model.addAttribute("posts",postsService.findAllDesc());
        if (user != null) {
            log.info("현재 로그인된 유저" + user.getEmail());
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        //로그인 안되어있을 경우 mustache에서 처리함
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model,@LoginUser SessionUser user) {
        if (user != null) {
            //로그인이 된 사용자의 경우 글 등록페이지로 이동
            log.info("로그인이 된 사용자 입니다");
            model.addAttribute("user", user);
            model.addAttribute("userPicture", user.getPicture());
            return "posts-save";
        } else {
            //로그인이 안된 사용자의 경우 홈화면으로 리다이렉트
            //TODO: 리다이렉트아닌, 경고창 띄어주기
            log.info("로그인이 필요합니다");
            model.addAttribute("posts", postsService.findAllDesc());
            return "redirect:/";
        }


    }


    @GetMapping("/posts/update/{postId}")
    public String postsUpdate(@PathVariable Long postId, Model model,@LoginUser SessionUser user) {
        if (user != null) {
            log.info("로그인이 된 사용자 입니다");
            model.addAttribute("userPicture", user.getPicture());
            Boolean isValidate = postsService.checkValidUpdateUser(postsService.findByPostId(postId).getUserId(), user.getUserId(), postId);
            if (isValidate.equals(true)) {
                PostsResponseDto dto = postsService.findByPostId(postId);
                model.addAttribute("post", dto);
                return "posts-update";
            } else {
                //글을 작성한 사용자가 아닌 경우, index 페이지로 리다이렉트
                //TODO: 리다이렉트아닌, 경고창 띄어주기
                model.addAttribute("posts", postsService.findAllDesc());
                return "redirect:/";
            }
        } else {
            //로그인이 안된 사용자인 경우, index 페이지로 리다이렉트
            //TODO: 리다이렉트아닌, 경고창 띄어주기
            log.info("로그인이 필요합니다");
            model.addAttribute("posts", postsService.findAllDesc());
            return "redirect:/";
        }
    }

    @GetMapping("/posts/{postId}/comments")
    public String Comments(@PathVariable Long postId, Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("postId", postId);
        }
        model.addAttribute("comments", commentService.findAllByPostId(postId));
        return "comments";
    }


    @GetMapping("/posts/{postId}/comments/save")
    public String commentsSave(@PathVariable Long postId, Model model, @LoginUser SessionUser user) {
        if (user != null) {
            //로그인이 된 사용자의 경우 댓글 등록페이지로 이동
            log.info("로그인이 된 사용자 입니다");
            model.addAttribute("user", user);
            model.addAttribute("userPicture", user.getPicture());
            return "comments-save";
        } else {
            //로그인이 안된 사용자의 경우 홈화면으로 리다이렉트 or comments로 리다이렉트
            //TODO: 리다이렉트아닌, 경고창 띄어주기
            log.info("로그인이 필요합니다");
            model.addAttribute("posts", postsService.findAllDesc());
            return "redirect:/";
        }


    }

    @GetMapping("/comments/update/{commentId}/{postId}")
    public String commentsUpdate(@PathVariable Long commentId, @PathVariable Long postId, Model model, @LoginUser SessionUser user) {
        if (user != null) {
            log.info("로그인이 된 사용자 입니다");
            model.addAttribute("userPicture", user.getPicture());

            Boolean isValidate = commentService.checkValidUpdateUser(commentService.findByCommentId(commentId).getUserId(), user.getUserId(), commentId);

            if (isValidate.equals(true)) {
                CommentResponseDto dto = commentService.findByCommentId(commentId);
                model.addAttribute("comment", dto);
                return "comments-update";
            } else {
                //댓글을 작성한 사용자가 아닌 경우, comments 페이지로 리다이렉트
                //TODO: 리다이렉트아닌, 경고창 띄어주기
                model.addAttribute("comments", commentService.findAllByPostId(postId));
                return "comments";
            }
        } else {
            //로그인이 안된 사용자의 경우 comments 페이지로 리다이렉트
            //TODO: 리다이렉트아닌, 경고창 띄어주기
            model.addAttribute("comments", commentService.findAllByPostId(postId));
            return "comments";
        }


    }


}
