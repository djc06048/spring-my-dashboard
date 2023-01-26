package com.example.toyproject.web.controller.posts;

import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.posts.PostsRepository;
import com.example.toyproject.domain.users.Users;
import com.example.toyproject.domain.users.UsersRepository;
import com.example.toyproject.web.dto.posts.PostsResponseDto;
import com.example.toyproject.web.dto.posts.PostsSaveRequestDto;
import com.example.toyproject.web.dto.posts.PostsUpdateRequestDto;
import com.example.toyproject.web.dto.users.response.UserResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UsersRepository usersRepository;
//    @After
//    public void tearDown() throws Exception{
//        postsRepository.deleteAll();
//        usersRepository.deleteAll();
//    }

    @Test
    @Transactional
    public void save() throws Exception{
        //given
        String title="title4";
        String content="content4";
        String userId="3";
        PostsSaveRequestDto requestDto= PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();

        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:"+port)
                        .path("/api/v1/posts").encode()
                        .build().toUri();
        System.out.println("uri = " + uri);
        //when
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add("ContentType", "application/json");
        //HttpEntity<PostsSaveRequestDto> request=new HttpEntity<>(dto,headers);
        System.out.println(" ###INSERT START");
        ResponseEntity<PostsResponseDto> responseEntity=restTemplate.postForEntity(uri,requestDto,PostsResponseDto.class);
        System.out.println(" ###INSERT END");

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println("responseEntity.toString() = " + responseEntity.toString());
        if(responseEntity.getBody().getSuccess().equals(true)){
            assertThat(responseEntity.getBody().getUserId()).isEqualTo(Long.parseLong(userId));
            assertThat(responseEntity.getBody().getMessage()).isEqualTo("성공적으로 등록되었습니다");

        }else{
            assertThat(responseEntity.getBody().getMessage()).isEqualTo("해당하는 유저가 존재하지 않습니다");

        }


    }

    @Test
    @Transactional
    public void update() throws Exception{
        //given
        String title="updateTitle";
        String content="updateContent";
        PostsUpdateRequestDto requestDto= PostsUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .build();
        Long userId=2L;
        Long postId=3L;
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:"+port)
                .path("/api/v1/posts/{postId}/users/{userId}").encode()
                .build().expand(postId,userId).toUri();
        System.out.println("uri = " + uri);
        //when
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add("ContentType", "application/json");
        //HttpEntity<PostsSaveRequestDto> request=new HttpEntity<>(dto,headers);
        System.out.println(" ###UPDATE START");
        HttpEntity<PostsUpdateRequestDto> requestEntity=new HttpEntity<>(requestDto);
        ResponseEntity<PostsResponseDto> responseEntity=restTemplate.exchange(uri,HttpMethod.PUT,requestEntity,PostsResponseDto.class);
        System.out.println(" ###UPDATE END");

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println("responseEntity.toString() = " + responseEntity.toString());
        if(responseEntity.getBody().getSuccess().equals(true)){
            assertThat(responseEntity.getBody().getUserId()).isEqualTo(userId);
            assertThat(responseEntity.getBody().getMessage()).isEqualTo("성공적으로 수정되었습니다");

        }
        //오류나는 경우 테스트?
    }


    @Test
    @Transactional
    public void findById() throws Exception{
        Long postId=5L;
        String url="http://localhost:"+port+"/api/v1/posts/"+postId;
        ResponseEntity<PostsResponseDto> responseEntity=restTemplate.getForEntity(url, PostsResponseDto.class);
        System.out.println(responseEntity.getBody().toString());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        if(responseEntity.getBody().getSuccess().equals(true)){
            assertThat(responseEntity.getBody().getPostId()).isEqualTo(postId);
            assertThat(responseEntity.getBody().getMessage()).isEqualTo("해당하는 게시물을 불러왔습니다");

        }else{
            assertThat(responseEntity.getBody().getMessage()).isEqualTo("해당 게시글이 없습니다. id="+postId);
        }
    }
}
