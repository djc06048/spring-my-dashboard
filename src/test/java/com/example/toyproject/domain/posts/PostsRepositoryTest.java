package com.example.toyproject.domain.posts;



import com.example.toyproject.domain.users.Users;
import com.example.toyproject.domain.users.UsersRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @Autowired
    UsersRepository usersRepository;



//    @After
//    public void cleanup(){
//        usersRepository.deleteAll();
//        postsRepository.deleteAll();
//    }

    @Test
    public void post_save() {
        //given
        String name = "cheol";
        String pw = "123";
        Users user = usersRepository.save(Users.builder().password(pw).name(name).build());



        String title = "title";
        String content = "content";
        Posts post = postsRepository.save(Posts.builder().title(title).content(content).writer(user.getEmail()).build());

        //when
        List<Posts> postsList = postsRepository.findByTitle(title);


        //then


        assertThat(postsList.get(postsList.size()-1).getTitle()).isEqualTo(title);
        assertThat(postsList.get(postsList.size()-1).getContent()).isEqualTo(content);
        assertThat(postsList.get(postsList.size()-1).getUser().getName()).isEqualTo(name);
    }
//    @Test
//
//    public void BaseTimEntity_save(){
//        //given
//        LocalDateTime now=LocalDateTime.of(2023,1,12,0,0,0);
//        postsRepository.save(Posts.builder().content("content").title("title").author(Users.builder().email("@@").password("123").username("hye").build()).build());
//        //when
//        List<Posts> postsList=postsRepository.findAll();
//        //then
//        Posts posts=postsList.get(0);
//        System.out.println("posts.getCreateAt() = " + posts.getCreateAt());
//        System.out.println("posts.getLastModified() = " + posts.getLastModified());
//
//        assertThat(posts.getCreateAt()).isAfter(now);
//        assertThat(posts.getLastModified()).isAfter(now);
//    }






}
