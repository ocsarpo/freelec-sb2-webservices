package org.jojo.book.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.jojo.book.springboot.domain.posts.Posts;
import org.jojo.book.springboot.domain.posts.PostsRepository;
import org.jojo.book.springboot.web.dto.PostsListResponseDto;
import org.jojo.book.springboot.web.dto.PostsResponseDto;
import org.jojo.book.springboot.web.dto.PostsSaveRequestDto;
import org.jojo.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        
        // JPA 영속성 컨텍스트 유지 상태이기 때문에 트랜잭션이 끝날 때 테이블에 변경을 반영함
        // 더티 체킹..
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
//        postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 Dto로 변환 후 List로 반환하는 메소드
        // -> map(posts -> new PostsListResponseDto(posts))와 동일한 람다식
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없음 id=" + id));
        postsRepository.delete(posts);
    }
}
