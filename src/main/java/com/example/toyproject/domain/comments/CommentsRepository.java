package com.example.toyproject.domain.comments;

import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    List<Comments> findByPost_PostId(Long postId);
    Optional<Comments> findByUserAndPost(Users user, Posts post);
}
