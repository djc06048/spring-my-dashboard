package com.example.toyproject.service.comments;

import com.example.toyproject.domain.comments.Comments;
import com.example.toyproject.domain.comments.CommentsRepository;
import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.posts.PostsRepository;
import com.example.toyproject.domain.user.User;
import com.example.toyproject.domain.user.UserRepository;
import com.example.toyproject.web.dto.comments.CommentResponseDto;
import com.example.toyproject.web.dto.comments.CommentsListResponseDto;
import com.example.toyproject.web.utils.WrongCommentExceptions;
import com.example.toyproject.web.utils.WrongPostsExceptions;
import com.example.toyproject.web.utils.WrongUserExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostsRepository postsRepository;
    private final UserRepository usersRepository;
    private final CommentsRepository commentsRepository;

    @Transactional(readOnly = true)
    public List<CommentsListResponseDto> findAllByPostId(Long postId){
        return commentsRepository
                .findByPost_PostId(postId)
                .stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());


    }
    @Transactional
    public CommentResponseDto saveComment(Long postId, String content, Long userId) {
        User user=usersRepository.findByUserId(userId).orElseThrow(()->new WrongUserExceptions("해당하는 유저가 존재하지 않습니다"));
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
    @Transactional
    public CommentResponseDto updateComment(Long postId, String content, Long userId) {
        User user=usersRepository.findByUserId(userId).orElseThrow(()->new WrongUserExceptions("해당하는 유저가 존재하지 않습니다"));
        Posts post=postsRepository.findByPostId(postId).orElseThrow(()->new WrongPostsExceptions("해당하는 게시물이 존재하지 않습니다."));
        Comments comment=commentsRepository.findByUserAndPost(user,post).orElseThrow(()->new WrongCommentExceptions("해당하는 댓글이 존재하지 않습니다."));
        comment.update(content);
        return new CommentResponseDto(true,"성공적으로 수정되었습니다",comment);
    }
    @Transactional(readOnly = true)
    public CommentResponseDto findByCommentId(Long commentId) {
        Comments entity=commentsRepository.findById(commentId)
                .orElseThrow(()->new WrongCommentExceptions("해당하는 댓글이 없습니다. id="+commentId));
        return new CommentResponseDto(true,"해당하는 댓글을 불러왔습니다",entity);
    }
}
