package com.example.toyproject.domain.comments;

import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments,Long> {

    Optional<Comments> findByUserAndPost(User user, Posts post);
    List<Comments> findByPost_PostId(Long postId);


}
