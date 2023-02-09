package com.example.toyproject.domain.user;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {
    @Autowired
    UserRepository usersRepository;



    @Test
    public void user_save(){
        //given
        String email = "blue@kakao.com";
        String pw = "1111";
        String name="blue";
        User user = usersRepository.save(User.builder().password(pw).email(email).name(name).build());
        //when
        List<User> usersList=usersRepository.findAll();


        //then
        assertThat(usersList.get(usersList.size()-1).getEmail()).isEqualTo(email);
    }
}
