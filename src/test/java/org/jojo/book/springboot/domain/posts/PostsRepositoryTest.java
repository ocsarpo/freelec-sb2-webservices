package org.jojo.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setMaxElementsForPrinting;

@RunWith(SpringRunner.class)
@SpringBootTest     // 별다른 설정 없이 쓰면 H2 DB를 자동으로 실행함
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // JUnit에서 단위 테스트가 끝날 때 마다 수행되는 메소드 지정
    // 배포 전 전체 테스트 수행 시 테스트 간 데이터 침범 막기 위해 사용
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("ocsarpo22@gmail.com")
        .build());      // posts테이블에 insert/update 쿼리 실행. (Upsert를 실행함)

        //when
        List<Posts> postsList = postsRepository.findAll(); // 모든 데이터 조회

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
