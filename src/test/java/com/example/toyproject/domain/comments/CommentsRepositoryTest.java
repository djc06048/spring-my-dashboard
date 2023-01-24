//package com.example.toyproject.domain.comments;
//
//
//import com.example.toyproject.domain.posts.Posts;
//import com.example.toyproject.domain.posts.PostsRepository;
//import com.example.toyproject.domain.users.Users;
//import com.example.toyproject.domain.users.UsersRepository;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Transactional
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CommentsRepositoryTest {
//    @Autowired
//    PostsRepository postsRepository;
//    @Autowired
//    UsersRepository usersRepository;
//    @Autowired
//    CommentsRepository commentsRepository;
//
//
////    @After
////    public void cleanup(){
////
////        usersRepository.deleteAll();
////        commentsRepository.deleteAll();
////        postsRepository.deleteAll();
////    }
//    @Test
//    public void comment_save() {
//
//        //given
//        String name = "cheol";
//        String pw = "123";
//        Users user = usersRepository.save(Users.builder().password(pw).name(name).build());
//
//
//
//        String title = "title";
//        String content = "content";
//        Posts post = postsRepository.save(Posts.builder().title(title).content(content).writer(user.getEmail()).build());
//
//        String comment_name = "park";
//        String comment_pw = "1234";
//        Users comment_user= usersRepository.save(Users.builder().password(comment_pw).name(comment_name).build());
//
//        String commentContent = "축하드려요~";
//        commentsRepository.save(Comments.builder().content(commentContent).user(comment_user).post(post).build());
//
//        //when
//
//        //해당 포스트 pk으로 등록된 모든 comment 조회
//        List<Comments> commentsList = commentsRepository.findByPost_PostId(1L);
//
//        //then
//
//        assertThat(commentsList.get(0).getUser().getName()).isEqualTo("park");
//    }
//}
