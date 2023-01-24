package com.example.toyproject.domain.comments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    List<Comments> findByPost_PostId(Long postId);
}
