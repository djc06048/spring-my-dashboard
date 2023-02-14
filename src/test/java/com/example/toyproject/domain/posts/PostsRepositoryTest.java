package com.example.toyproject.domain.posts;



import com.example.toyproject.domain.user.User;
import com.example.toyproject.domain.user.UserRepository;
import com.example.toyproject.web.utils.WrongUserExceptions;
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
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    public void post_save() {
        //given
        String user_email = "gsafe1213@gmail.com";
        User user = userRepository.findByEmail(user_email).orElseThrow(() -> new WrongUserExceptions("해당 하는 유저가 존재하지 않습니다"));

        String title = "테스트용 글 제목";
        String content = "테스트용 글 내용";
        postsRepository.save(Posts.builder().title(title).content(content).writer(user.getEmail()).user(user).build());

        //when
        List<Posts> postsList = postsRepository.findByTitle(title);


        //then

        assertThat(postsList.get(postsList.size() - 1).getTitle()).isEqualTo(title);
        assertThat(postsList.get(postsList.size() - 1).getContent()).isEqualTo(content);

    }


}
