package org.jojo.book.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.jojo.book.springboot.domain.posts.Posts;
import org.jojo.book.springboot.domain.posts.PostsRepository;
import org.jojo.book.springboot.web.dto.PostsResponseDto;
import org.jojo.book.springboot.web.dto.PostsSaveRequestDto;
import org.jojo.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// final 이 선언된 모든 필드를 인자값으로 하는 생성자를 생성함.
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없음. id= " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

//    @Transactional(readOnly = true)
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));

        return new PostsResponseDto(entity);
    }
}
