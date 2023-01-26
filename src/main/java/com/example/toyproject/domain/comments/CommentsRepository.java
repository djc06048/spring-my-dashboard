package com.example.toyproject.domain.comments;

import com.example.toyproject.domain.posts.Posts;
import com.example.toyproject.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments,Long> {

    Optional<Comments> findByUserAndPost(Users user, Posts post);
    List<Comments> findByPost_PostId(Long postId);
}
