package org.jojo.book.springboot.config;

import lombok.RequiredArgsConstructor;
import org.jojo.book.springboot.config.auth.LoginUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

//        HandlerMethodArgumentResolver 는 항상 WebMvcConfigurer의 addArgumentResolvers를 통해 추가해야한다.
        // 다른 HandlerMethodArgumentResolver가 필요하면 같은 방식으로 추가 하면 됨
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
