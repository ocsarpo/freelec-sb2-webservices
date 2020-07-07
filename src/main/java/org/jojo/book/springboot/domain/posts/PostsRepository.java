package org.jojo.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity 클래스, PK 타입> 상속하면 기본적인 CRUD 메소드가 자동 생성됨
// Entity 클래스와 Repository는 함께 움직여야 하므로 도메인 패키지에서 함께 관리함.
public interface PostsRepository extends JpaRepository <Posts, Long> {
}
