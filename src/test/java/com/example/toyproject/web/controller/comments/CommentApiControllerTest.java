package com.example.toyproject.web.controller.comments;

import com.example.toyproject.domain.posts.PostsRepository;
import com.example.toyproject.domain.users.UsersRepository;
import com.example.toyproject.web.dto.comments.CommentResponseDto;
import com.example.toyproject.web.dto.comments.CommentSaveRequestDto;
import com.example.toyproject.web.dto.comments.CommentUpdateRequestDto;
import com.example.toyproject.web.dto.posts.PostsResponseDto;
import com.example.toyproject.web.dto.posts.PostsUpdateRequestDto;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UsersRepository usersRepository;
    @Test
    @Transactional
    public void saveComment() throws Exception{
        //given
        String content="comment content";
        Long postId=1L;
        Long userId=3L;
        CommentSaveRequestDto requestDto=CommentSaveRequestDto
                .builder()
                .content(content)
                .postId(postId)
                .userId(userId)
                .build();
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:"+port)
                .path("/api/v1/comments").encode()
                .build().toUri();
        System.out.println("uri = " + uri);
        //when
        System.out.println(" ###INSERT START");
        ResponseEntity<CommentResponseDto> responseEntity = restTemplate.postForEntity(uri, requestDto, CommentResponseDto.class);
        System.out.println(" ###INSERT END");
        System.out.println("responseEntity.toString() = " + responseEntity.toString());
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        if(responseEntity.getStatusCode().equals(true)){
            assertThat(responseEntity.getBody().getUserId()).isEqualTo(userId);
            assertThat(responseEntity.getBody().getPostId()).isEqualTo(postId);
        }


    }

    @Test
    @Transactional
    public void updateComment() throws Exception{
        String content="updateComment";
        Long postId=1L;
        Long userId=3L;
        CommentUpdateRequestDto requestDto=CommentUpdateRequestDto
                .builder()
                .userId(userId)
                .postId(postId)
                .content(content)
                .build();
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:"+port)
                .path("/api/v1/comments").encode()
                .build().toUri();
        System.out.println("uri = " + uri);
        //when
        System.out.println(" ###UPDATE START");
        HttpEntity<CommentUpdateRequestDto> requestEntity=new HttpEntity<>(requestDto);
        ResponseEntity<CommentResponseDto> responseEntity=restTemplate.exchange(uri, HttpMethod.PUT,requestEntity,CommentResponseDto.class);
        System.out.println(" ###UPDATE END");
        System.out.println("responseEntity.toString() = " + responseEntity.toString());
        if(responseEntity.getBody().getSuccess().equals(true)){
            assertThat(responseEntity.getBody().getUserId()).isEqualTo(userId);
            assertThat(responseEntity.getBody().getComment()).isEqualTo(content);

        }
    }
}