package org.jojo.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository<Entity 클래스, PK 타입> 상속하면 기본적인 CRUD 메소드가 자동 생성됨
// Entity 클래스와 Repository는 함께 움직여야 하므로 도메인 패키지에서 함께 관리함.
public interface PostsRepository extends JpaRepository <Posts, Long> {
    // SpringDataJpa에서 제공하지 않는 메소드는 아래와 같이 작성해도 됨
//    QueryDSL...!

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
