package com.example.toyproject.domain.comments;

import com.example.toyproject.domain.bulletin.Bulletin;
import com.example.toyproject.domain.bulletin.BulletinRepository;
import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.posts.PostsRepository;
import com.example.toyproject.domain.users.Users;
import com.example.toyproject.domain.users.UsersRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    BulletinRepository bulletinRepository;

    @After
    public void cleanup(){
        bulletinRepository.deleteAll();
        usersRepository.deleteAll();
        commentsRepository.deleteAll();
        postsRepository.deleteAll();
    }
    @Test
    public void comment_save() {

        //given
        String username = "cheol";
        String email = "cheol@naver.com";
        String pw = "123";
        Users user = usersRepository.save(Users.builder().email(email).password(pw).username(username).build());

        String category = "자유게시판";
        Bulletin bulletin = bulletinRepository.save(Bulletin.builder().title(category).build());


        String title = "title";
        String content = "content";
        Posts post = postsRepository.save(Posts.builder().title(title).content(content).author(user).bulletin(bulletin).build());

        String comment_username = "park";
        String comment_email = "park@naver.com";
        String comment_pw = "1234";
        Users comment_user= usersRepository.save(Users.builder().email(comment_email).password(comment_pw).username(comment_username).build());

        String commentContent = "축하드려요~";
        commentsRepository.save(Comments.builder().content(commentContent).author(comment_user).post(post).build());

        //when

        //해당 포스트 pk으로 등록된 모든 comment 조회
        List<Comments> commentsList = commentsRepository.findByPosts_PostId(1L);

        //then

        assertThat(commentsList.size()).isEqualTo(1);
    }
}
