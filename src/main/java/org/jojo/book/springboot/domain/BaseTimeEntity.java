package org.jojo.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass       // 클래스를 상속받는 경우 필드들도 칼럼으로 인식하도록 함.
@EntityListeners(AuditingEntityListener.class)      // 클래스에 Auditing 기능을 포함시킴
public abstract class BaseTimeEntity {

    @CreatedDate        // Entity가 생성되어 저장될 때 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate       // Entity가 수정될 때 시간 자동 저장
    private LocalDateTime modifiedDate;
}
