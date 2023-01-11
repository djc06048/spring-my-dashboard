package com.example.toyproject.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    List<Posts> findByTitle(String title);
    List<Posts> findByPostId(Long postId);
}
