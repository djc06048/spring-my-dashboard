package com.example.toyproject.service.comments;

import com.example.toyproject.config.auth.LoginUser;
import com.example.toyproject.config.auth.dto.SessionUser;
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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final PostsRepository postsRepository;
    private final UserRepository usersRepository;
    private final CommentsRepository commentsRepository;

    //특정 포스트의 모든 댓글 불러오기
    @Transactional(readOnly = true)
    public List<CommentsListResponseDto> findAllByPostId(Long postId) {
        return commentsRepository
                .findByPost_PostId(postId)
                .stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());


    }

    //postId, userId, content를 body로 받아서 comment 저장
    //comment를 작성하는 경우, postId는 반드시 존재, user는 존재하지 않을 수 있다.(로그인 되어이있지 않은 경우)
    @Transactional
    public CommentResponseDto saveComment(Long postId, String content, Long userId) {

        User user = usersRepository.getReferenceById(userId);
        Posts posts = postsRepository.getReferenceById(postId);
        Comments comment = Comments.builder()
                .writer(user.getEmail())
                .content(content)
                .post(posts)
                .user(user)
                .build();


        //id를 가져와서 넣어주냐, 연관관계를 통해 연결하냐의 차이 => find, get


        //댓글 저장
        Comments savedComment = commentsRepository.save(comment);

        return new CommentResponseDto(true, "댓글 등록 성공", savedComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long postId, String content, Long userId) {
        User user = usersRepository.findByUserId(userId).orElseThrow(() -> new WrongUserExceptions("해당하는 유저가 존재하지 않습니다"));
        Posts post = postsRepository.findByPostId(postId).orElseThrow(() -> new WrongPostsExceptions("해당하는 게시물이 존재하지 않습니다."));
        Comments comment = commentsRepository.findByUserAndPost(user, post).orElseThrow(() -> new WrongCommentExceptions("해당하는 댓글이 존재하지 않습니다."));
        comment.update(content);
        return new CommentResponseDto(true, "성공적으로 수정되었습니다", comment);
    }

    //특정 commentId로 조회시 comment 가져옴
    @Transactional(readOnly = true)
    public CommentResponseDto findByCommentId(Long commentId) {
        Comments entity = commentsRepository.findById(commentId)
                .orElseThrow(() -> new WrongCommentExceptions("해당하는 댓글이 없습니다. id=" + commentId));
        return new CommentResponseDto(true, "해당하는 댓글을 불러왔습니다", entity);
    }

    public boolean checkValidUpdateUser(Long commentUserId, Long nowUserId, Long commentId) {
        usersRepository.findByUserId(nowUserId).orElseThrow(() -> new WrongUserExceptions("로그인 되지 않은 사용자 입니다"));
        commentsRepository.findById(commentId).orElseThrow(() -> new WrongCommentExceptions("해당하는 댓글이 존재하지 않습니다."));
        if (nowUserId.equals(commentUserId)) {
            log.info("해당 댓글을 작성한 사용자이므로 수정이 가능합니다");
            return true;
        } else {
            log.info("해당 댓글을 작성한 사용자가 아닙니다");
            return false;
        }

    }
}
