package com.example.toyproject.domain.bulletin;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BulletinRepositoryTest {
    @Autowired
    BulletinRepository bulletinRepository;

    @After
    public void cleanup(){
        bulletinRepository.deleteAll();
    }

    @Test
    public void bulletin_save(){
        //given
        String category = "자유게시판";
        Bulletin bulletin = bulletinRepository.save(Bulletin.builder().title(category).build());

        //when
        Integer num=bulletinRepository.findByTitle("자유게시판").size(); //해당 게시판의 개수 반환

        //then
        assertThat(num).isEqualTo(1);
    }
}
