package org.jojo.book.springboot.config.auth;

// 시큐리티관련 클래스는 모두 config.auth 패키지에 담는다.

import lombok.RequiredArgsConstructor;
import org.jojo.book.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity      // 스프링 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()      // h2 console 화면을 사용하기 위해 해당 옵션 비활성화
                .and()
                .authorizeRequests()                    // URL 별 권한 관리 설정 옵션의 시작 이게 있어야 antMatchers 사용 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()      // 전체 열람 가능 (인증없이 호출 가능)
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())        // 권한관리 대상 지정. URL, HTTP 메소드 별 관리 가능 // USER 권한만 가능
                .anyRequest().authenticated()   // 설정된 값 외 나머지 URL은 인증된 사람만 허용(로그인유저)
                .and()
                .logout()   // 로그아웃 기능에 대한 설정 진입
                .logoutSuccessUrl("/")  // 로그아웃 성공시 /로 이동  
                .and()
                .oauth2Login()  // OAuth2 설정 진입
                .userInfoEndpoint()     // OAuth2 로그인 성공 직후 사용자 정보 가져올 때의 설정들
                .userService(customOAuth2UserService);  // 소셜 로그인 성공 시 후속조치를 진행할 UserService 인터페이스 구현체 등록
        // 리소스서버(구글 등)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능 (userService)
    }
}
