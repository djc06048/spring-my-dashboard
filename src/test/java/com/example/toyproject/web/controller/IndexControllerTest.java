package com.example.toyproject.web.controller;

import com.example.toyproject.domain.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.toyproject.domain.user.Role.USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void index() {
        //when
        String body=this.restTemplate.getForObject("/",String.class);
        System.out.println("body = " + body);
        //then
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
        assertThat(userRepository.findByEmail("gsafe1213@gmail.com").get().getRole()).isEqualTo(USER);
    }
}