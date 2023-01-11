TRUNCATE TABLE bulletin;
INSERT INTO bulletin(title)
VALUES
    (1L,"notice"),
    (2L,"contest");

TRUNCATE TABLE comments;
INSERT INTO comments(comment_id,content,author_id,post_id)
VALUES
    (1L,"확인했습니다",1,1),
    (2L,"축하드려요~",2,2);


TRUNCATE TABLE posts;
INSERT INTO posts(post_id,content,like_cnt,read_cnt,title,author_id,bulletin_id)
VALUES
    (1L,"주목해주세요...",1,1,"주목",1L,1L),
    (2L,"대회 수상자 발표합니다...",10,4,"발표",2L,2L);

TRUNCATE TABLE user;
INSERT INTO user(user_id,email,password,username)
VALUES
    (1L,"djc06048@gmail.com","1234","hyelim"),
    (2L,"ejfj@naver.com","2222","yejin");
