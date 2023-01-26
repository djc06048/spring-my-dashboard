package com.example.toyproject.service.posts;

import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.posts.PostsRepository;

import com.example.toyproject.domain.users.Users;
import com.example.toyproject.domain.users.UsersRepository;
import com.example.toyproject.service.users.UserService;
import com.example.toyproject.web.dto.posts.PostsListResponseDto;
import com.example.toyproject.web.dto.posts.PostsResponseDto;
import com.example.toyproject.web.dto.posts.PostsSaveRequestDto;
import com.example.toyproject.web.dto.posts.PostsUpdateRequestDto;
import com.example.toyproject.web.utils.WrongPostsExceptions;
import com.example.toyproject.web.utils.WrongUserExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;
    private final UserService userService;
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }
    @Transactional
    public PostsResponseDto save(PostsSaveRequestDto req,Long userId) {
        Users user=usersRepository.findByUserId(userId).orElseThrow(()->new WrongUserExceptions("해당하는 유저가 존재하지 않습니다"));
        Posts post=Posts.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .writer(user.getEmail())
                .build();
        Posts savedPost=postsRepository.save(post);
        user.writePost(savedPost);
        return new PostsResponseDto(true,"성공적으로 등록되었습니다",post);
    }
    @Transactional
    public PostsResponseDto update(Long postId,Long userId, PostsUpdateRequestDto req) {
        Users user=usersRepository.findByUserId(userId).orElseThrow(()->new WrongUserExceptions("해당하는 유저가 존재하지 않습니다"));
        Posts post=postsRepository.findByPostId(postId).orElseThrow(()->new WrongPostsExceptions("해당하는 게시물이 존재하지 않습니다."));
        checkBoardValidUser(user,post);
        post.update(req.getTitle(), req.getContent());

        return new PostsResponseDto(true,"성공적으로 수정되었습니다",post);

    }

    private void checkBoardValidUser(Users user, Posts post) {
        if(!Objects.equals(post.getUser().getUserId(),user.getUserId())){
            throw new WrongPostsExceptions("해당 게시물을 수정할 권리가 없습니다.");
        }
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity=postsRepository.findById(id)
                .orElseThrow(()
                        ->new WrongPostsExceptions("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(true,"해당하는 게시물을 불러왔습니다",entity);
    }

}
