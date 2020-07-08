package org.jojo.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.jojo.book.springboot.service.posts.PostsService;
import org.jojo.book.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 페이지에 관련된 컨트롤러는 모두 Index에 위치
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { // 서버템플릿엔진에서 사용할 수 있는 객체 저장
        // postsService.findALlDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());

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
