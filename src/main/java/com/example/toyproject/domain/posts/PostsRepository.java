package com.example.toyproject.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByTitle(String title);

    Optional<Posts> findByPostId(Long postId);

    @Query("SELECT p FROM Posts p ORDER BY p.postId DESC ")
    List<Posts> findAllDesc();

    //    @Query("select p from Posts p where p.title like %:keyword%")
    List<Posts> findByTitleContaining(String keyword);
    //JpaRepository에서 메소드명의 By 이후는 SQL의 where 조건 절에 대응되는데,
    //위와 같이 Containing을 붙여주면 Like 검색이 가능해진다. 즉, %{keyword}%가 가능

}
