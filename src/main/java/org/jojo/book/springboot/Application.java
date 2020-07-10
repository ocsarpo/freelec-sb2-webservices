package org.jojo.book.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Entity를 다루지 않는 컨트롤러 메서드 테스트 시 발생하는 문제(스캔관련)로 인해 제거 P221
// @WebMvc는 일반적인 @Configuration을 스캔하지 않음.
// config/JpaConfig.java 생성하여 추가함
//@EnableJpaAuditing      // Jpa Auditing 활성화 (목적: BaseTimeEntity -> 생성,수정시간 자동기록)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
