package org.jojo.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.jojo.book.springboot.config.auth.LoginUser;
import org.jojo.book.springboot.config.auth.dto.SessionUser;
import org.jojo.book.springboot.domain.user.User;
import org.jojo.book.springboot.service.posts.PostsService;
import org.jojo.book.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

// 페이지에 관련된 컨트롤러는 모두 Index에 위치
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // 서버템플릿엔진에서 사용할 수 있는 객체 저장
        // postsService.findALlDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());

//      아래는 세션값을 가져오는 코드이지만, index 이외에 곳에서 세션값이 필요하다면
// 동일한 코드를 작성해야만 한다. 그래서 이 부분을 "메소드 인자로 세션값을 바로 받을 수 있도록" 변경한다
// org/jojo/book/springboot/config/auth/LoginUser
// org/jojo/book/springboot/config/auth/LoginUserArgumentResolver
// org/jojo/book/springboot/config/WebConfig
// 아래 코드를 25라인 @LoginUser SessionUser user로 대체 가능 이제 어느 컨트롤러이든지 세션정보를 파라미터로 가져올 수 있게 되었다
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

//        mustache 스타터가 리턴값으로 리턴할 파일 경로로 전환 -> View Resolver가 처리
        // src/main/resources/templates/index.mustache
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }


}
