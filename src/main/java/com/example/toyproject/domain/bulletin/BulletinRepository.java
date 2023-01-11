package com.example.toyproject.domain.bulletin;

import com.example.toyproject.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BulletinRepository extends JpaRepository<Bulletin,Long> {
    //특정 카테고리로 게시판 데이터베이스 조회시 해당 게시판 개수 반환하기 위해 리스트로 객체 반환
    List<Bulletin> findByTitle(String category);
}
