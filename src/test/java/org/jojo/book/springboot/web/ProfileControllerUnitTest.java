package org.jojo.book.springboot.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {
    @Test
    public void real_profile이_조회된다(){
        //given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");
        
//        ProfileController 에서 생성자DI(의존성주입) 을 통해 Environment를 받아서
//        아래와 같이 가능. 둘 다 자바클래스(인터페이스)라 가능
//        만약 컨트롤러에서 Env를 @Autowired로 받았으면 아래처럼 못쓰고, 스프링테스트를 진행해야함
        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void active_profile이_없으면_default가_조회된다(){
        //given
        String exptectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(exptectedProfile);
    }
}
