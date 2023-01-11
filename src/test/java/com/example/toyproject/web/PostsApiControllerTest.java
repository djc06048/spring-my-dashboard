//package com.example.toyproject.web;
//
//import com.example.toyproject.domain.posts.Posts;
//import com.example.toyproject.domain.posts.PostsRepository;
//import com.example.toyproject.web.dto.PostsSaveRequestDto;
//import org.junit.After;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PostsApiControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @After
//    public void tearDown() throws Exception{
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    public void Posts_등록된다() throws Exception{
//        //given
//        String title="title";
//        String content="content";
//
//        PostsSaveRequestDto requestDto= PostsSaveRequestDto.builder().title(title).content(content).author("author").readCnt(0).likeCnt(0).build();
//
//        String url="http://localhost:"+this.port+"/api/v1/posts";
//        //when
//        ResponseEntity<Long> reponseEntity=this.restTemplate.postForEntity(url,requestDto,Long.class);
//
//        //then
//        assertThat(reponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(reponseEntity.getBody()).isGreaterThan(0L);
//        List<Posts> all=postsRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//    }
//
//}
