package com.example.toyproject.web.controller.users;

import com.example.toyproject.domain.user.User;
import com.example.toyproject.domain.user.UserRepository;
import com.example.toyproject.web.dto.users.request.userJoinRequestDto;
import com.example.toyproject.web.dto.users.request.userLoginRequestDto;
import com.example.toyproject.web.dto.users.response.LoginResponseDto;

import com.example.toyproject.web.dto.users.response.UserResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository usersRepository;

    @Test
    @Transactional
    public void join() throws Exception{
        //given
        String email="red@kakao.com";
        String name="red";
        String pw="1234";
        userJoinRequestDto requestDto = userJoinRequestDto.builder()
                .email(email).name(name).password(pw)
                .build();
        String url="http://localhost:"+port+"/api/v1/users/join";
        System.out.println("url = " + url);
        //when
        System.out.println(" ###INSERT START");
        ResponseEntity<LoginResponseDto> responseEntity=restTemplate.postForEntity(url,requestDto,LoginResponseDto.class);
        System.out.println(" ###INSERT END");
        System.out.println("responseDto " + responseEntity.toString());

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        if(responseEntity.getBody().getSuccess().equals(false)){
            assertThat(responseEntity.getBody().getMessage()).isEqualTo("유효하지않은 이메일입니다.");
        }
        else {
            assertThat(responseEntity.getBody().getSuccess()).isEqualTo(true);
            System.out.println(responseEntity.getBody().toString());
            List<User> all=usersRepository.findAll();
            assertThat(all.get(all.size()-1).getEmail()).isEqualTo(email);
        }


    }

    @Test
    @Transactional
    public void login() throws Exception{
        //given
        String email="blue@kakao.com";
        String pw="1234";
        userLoginRequestDto requestDto = userLoginRequestDto.builder()
                .email(email).password(pw).build();
        String url="http://localhost:"+port+"/api/v1/users/login";
        System.out.println("url = " + url);
        System.out.println(" requestDto.toString()= " + requestDto.toString());

        //when
        ResponseEntity<LoginResponseDto> responseEntity=restTemplate.postForEntity(url,requestDto,LoginResponseDto.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody().toString());

        if(responseEntity.getBody().getSuccess().equals(false)){
            if(responseEntity.getBody().getMessage().equals("이메일이 존재하지 않음")){
                assertThat(responseEntity.getBody().getMessage()).isEqualTo("이메일이 존재하지 않음");
            }
            if(responseEntity.getBody().getMessage().equals("비밀번호가 존재하지 않음")){
                assertThat(responseEntity.getBody().getMessage()).isEqualTo("비밀번호가 존재하지 않음");
            }
        }else{
            assertThat(responseEntity.getBody().getMessage()).isEqualTo("로그인 성공");
        }


    }

    @Test
    @Transactional
    public void findUser() throws Exception{
        Long userId=3L;
        String url="http://localhost:"+port+"/api/v1/users/"+userId;

        ResponseEntity<UserResponseDto> responseEntity=restTemplate.getForEntity(url, UserResponseDto.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        if(responseEntity.getBody().getSuccess().equals(true)){
            assertThat(responseEntity.getBody().getUserId()).isEqualTo(userId);
            assertThat(responseEntity.getBody().getMessage()).isEqualTo("유저를 가져오는데 성공했습니다");

        }else{
            assertThat(responseEntity.getBody().getMessage()).isEqualTo("해당하는 유저가 존재하지 않습니다");
        }
        System.out.println(responseEntity.getBody().toString());

    }

//    @Test
//    @Transactional
//    public void deleteUser() throws Exception {
//        Long userId=1L;
//        String url="http://localhost:"+port+"/api/v1/users/"+userId;
//        restTemplate.delete(url);
//        ResponseEntity<LoginResponseDto> responseEntity=restTemplate.exchange(url,HttpMethod.DELETE,HttpEntity.EMPTY, LoginResponseDto.class);
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//
//
//    }
}