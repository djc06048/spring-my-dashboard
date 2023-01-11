package com.example.toyproject.domain.posts;

import com.example.toyproject.domain.bulletin.Bulletin;
import com.example.toyproject.domain.bulletin.BulletinRepository;
import com.example.toyproject.domain.comments.Comments;
import com.example.toyproject.domain.comments.CommentsRepository;
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
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    BulletinRepository bulletinRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @After
    public void cleanup(){
        bulletinRepository.deleteAll();
        usersRepository.deleteAll();
        commentsRepository.deleteAll();
        postsRepository.deleteAll();
    }

    @Test
    public void post_save() {
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

        //when
        List<Posts> postsList = postsRepository.findByTitle(title);


        //then


        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
        assertThat(postsList.get(0).getContent()).isEqualTo(content);
        assertThat(postsList.get(0).getBulletin().getBulletinId()).isEqualTo(1L);
        assertThat(postsList.get(0).getAuthor().getUserId()).isEqualTo(1L);
    }






}
