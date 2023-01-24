package com.example.toyproject.service.comments;

import com.example.toyproject.domain.comments.Comments;
import com.example.toyproject.domain.comments.CommentsRepository;
import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.posts.PostsRepository;
import com.example.toyproject.domain.users.Users;
import com.example.toyproject.domain.users.UsersRepository;
import com.example.toyproject.web.dto.comments.CommentResponseDto;
import com.example.toyproject.web.dto.comments.CommentSaveRequestDto;
import com.example.toyproject.web.utils.WrongPostsExceptions;
import com.example.toyproject.web.utils.WrongUserExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;
    private final CommentsRepository commentsRepository;
    public CommentResponseDto saveComment(Long postId, String content, Long userId) {
        Users user=usersRepository.findByUserId(userId).orElseThrow(()->new WrongUserExceptions("해당하는 유저가 존재하지 않습니다"));
        Posts post=postsRepository.findByPostId(postId).orElseThrow(()->new WrongPostsExceptions("해당하는 게시물이 존재하지 않습니다."));
        Comments comment= Comments.builder()
                .writer(user.getEmail())
                .content(content)
                .build();
        Comments savedComment=commentsRepository.save(comment);
        user.writeComment(savedComment);
        post.writeComment(savedComment);
        return new CommentResponseDto(true,"댓글 등록 성공",savedComment);
    }
}
