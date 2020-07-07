package org.jojo.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter  // getter 생성 (테스트로 확인하였음)
@RequiredArgsConstructor // 생성자 생성 (테스트로 확인하였음)
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
