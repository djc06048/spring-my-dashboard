package com.example.toyproject.domain.posts;



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



    @After
    public void cleanup(){
        usersRepository.deleteAll();
        postsRepository.deleteAll();
    }

    @Test
    public void post_save() {
        //given
        String username = "cheol";
        String email = "cheol@naver.com";
        String pw = "123";
        Users user = usersRepository.save(Users.builder().email(email).password(pw).username(username).build());



        String title = "title";
        String content = "content";
        Posts post = postsRepository.save(Posts.builder().title(title).content(content).author(user).build());

        //when
        List<Posts> postsList = postsRepository.findByTitle(title);


        //then


        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
        assertThat(postsList.get(0).getContent()).isEqualTo(content);

        assertThat(postsList.get(0).getAuthor().getUserId()).isEqualTo(1L);
    }






}
