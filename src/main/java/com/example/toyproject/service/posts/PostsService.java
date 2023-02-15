package com.example.toyproject.service.posts;

import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.posts.PostsRepository;

import com.example.toyproject.domain.user.User;
import com.example.toyproject.domain.user.UserRepository;
import com.example.toyproject.service.users.UserService;
import com.example.toyproject.web.dto.posts.PostsListResponseDto;
import com.example.toyproject.web.dto.posts.PostsResponseDto;
import com.example.toyproject.web.dto.posts.PostsSaveRequestDto;
import com.example.toyproject.web.dto.posts.PostsUpdateRequestDto;
import com.example.toyproject.web.utils.WrongCommentExceptions;
import com.example.toyproject.web.utils.WrongPostsExceptions;
import com.example.toyproject.web.utils.WrongUserExceptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository usersRepository;
    private final UserService userService;


    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public PostsResponseDto save(PostsSaveRequestDto req) {
        Long userId = req.getUserId();

        User user = usersRepository.getReferenceById(userId);
        Posts post = Posts.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .writer(user.getEmail())
                .user(user)
                .build();

        Posts savedPost = postsRepository.save(post);

        return new PostsResponseDto(true, "성공적으로 등록되었습니다", savedPost);
    }
    @Transactional
    public PostsResponseDto update(Long postId,Long userId, PostsUpdateRequestDto req) {
        User user=usersRepository.findByUserId(userId).orElseThrow(()->new WrongUserExceptions("해당하는 유저가 존재하지 않습니다"));
        Posts post=postsRepository.findByPostId(postId).orElseThrow(()->new WrongPostsExceptions("해당하는 게시물이 존재하지 않습니다."));
        //user의 Id와 Post의 id가 다를 경우 오류 메시지
        checkBoardValidUser(user,post);
        post.update(req.getTitle(), req.getContent());

        return new PostsResponseDto(true,"성공적으로 수정되었습니다",post);

    }

    private void checkBoardValidUser(User user, Posts post) {
        if(!Objects.equals(post.getUser().getUserId(),user.getUserId())){
            throw new WrongPostsExceptions("해당 게시물을 수정할 권리가 없습니다.");
        }
    }

    //수정 위해 postId를 pathVariable로 받아 특정 포스트 불러오기
    @Transactional(readOnly = true)
    public PostsResponseDto findByPostId(Long postId) {
        //해당 포스트가 없을 경우 올 메시지, 성공할 경우 성공메시지
        Posts entity = postsRepository.findById(postId)
                .orElseThrow(()
                        -> new WrongPostsExceptions("해당 게시글이 없습니다. id=" + postId));
        return new PostsResponseDto(true, "해당하는 게시물을 불러왔습니다", entity);
    }

    @Transactional(readOnly = true)
    public Boolean checkValidUpdateUser(Long postUserId, Long nowUserId, Long postId) {
        usersRepository.findByUserId(nowUserId).orElseThrow(() -> new WrongUserExceptions("로그인 되지 않은 사용자 입니다"));
        postsRepository.findById(postId).orElseThrow(() -> new WrongPostsExceptions("해당하는 글이 존재하지 않습니다"));
        if (nowUserId.equals(postUserId)) {
            log.info("해당 글을 작성한 사용자이므로 수정이 가능합니다");
            return true;
        } else {
            log.info("해당 글을 작성한 사용자가 아닙니다");
            return false;
        }

    }

    /**
     * search
     */
    @Transactional
    public List<Posts> search(String keyword) {
        List<Posts> postsList = postsRepository.findByTitleContaining(keyword);
        return postsList;
    }


}
