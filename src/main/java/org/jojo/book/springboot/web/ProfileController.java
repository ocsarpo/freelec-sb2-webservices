package org.jojo.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile(){
        // 현재 실행중인 ActiveProfile을 모두 가져옴
        // ex) real, oauth, real-db 등이 활성화 되어 있다면 3개가 모두 담겨있음
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        // real, real1, real2는 모두 배포에 사용될 profile이기 때문에 하나라도 있으면 그 값 반환
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
