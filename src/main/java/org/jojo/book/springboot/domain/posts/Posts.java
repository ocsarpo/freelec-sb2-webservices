package org.jojo.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jojo.book.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter     // 클래스 내 모든 필드의 getter 생성
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity     // 테이블과 링크될 클래스임을 나타냄. 클래스의 카멜케이스 이름을 스네이크케이스 네이밍으로 테이블을 매칭함 (SalesManager.java -> sales_manager table)
public class Posts extends BaseTimeEntity {

    @Id     // PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PK생성규칙. IDENTITY를 추가해야 auto_increment됨
    private Long id;

    @Column(length = 500, nullable = false)     // 테이블의 컬럼. 굳이 선언안해도 클래스의 필드는 모두 칼럼이 됨.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)  // 사용이유는. 기본값 이외에 추가로 변경이 필요한 경우에 사용함.
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스 생성.
    @Builder        // 생성자 상단에 선언 시.. 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
