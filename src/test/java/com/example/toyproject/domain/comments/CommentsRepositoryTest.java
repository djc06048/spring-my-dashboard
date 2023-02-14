package com.example.toyproject.domain.comments;


import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.posts.PostsRepository;
import com.example.toyproject.domain.user.User;
import com.example.toyproject.domain.user.UserRepository;
import com.example.toyproject.web.utils.WrongPostsExceptions;
import com.example.toyproject.web.utils.WrongUserExceptions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentsRepository commentsRepository;

    @Test
    public void comment_save() {

        //given
        String user_email = "gsafe1213@gmail.com";
        User user = userRepository.findByEmail(user_email).orElseThrow(() -> new WrongUserExceptions("해당 하는 유저가 존재하지 않습니다"));

        Long post_id = 1L;
        Posts post = postsRepository.findByPostId(post_id).orElseThrow(() -> new WrongPostsExceptions("해당 하는 포스트가 존재하지 않습니다"));


        String commentContent = "테스트용 댓글";
        commentsRepository.save(Comments.builder().writer(user_email).content(commentContent).user(user).post(post).build());

        //when

        //해당 포스트 pk으로 등록된 모든 comment 조회
        List<Comments> commentsList = commentsRepository.findByPost_PostId(1L);

        //then

        assertThat(commentsList.get(0).getUser().getEmail()).isEqualTo(user_email);
    }
}
