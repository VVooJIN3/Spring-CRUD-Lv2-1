package com.crud.blog.service;

import com.crud.blog.dto.PostRequestDto;
import com.crud.blog.dto.PostResponseDto;
import com.crud.blog.entity.Post;
import com.crud.blog.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createBlogPost(PostRequestDto requestDto) {

        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // DB 저장
        Post savePost = postRepository.save(post); // 정보 저장

        // Entity -> ResponseDto로 변환 후 반환
        PostResponseDto postResponseDto = new PostResponseDto(savePost);

        return postResponseDto;
    }


    public List<PostResponseDto> getBlogPosts() {

        // DB 조회
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }
    public PostResponseDto getBlogPost(Integer id) {

        // 해당 게시글이 DB에 존재하는지 확인
        Post post = findBlogPost(id);

        // 게시글 조회
        return new PostResponseDto(post);
    }


    @Transactional
    public PostResponseDto updateBlogPost(Integer id, PostRequestDto requestDto) {

        // 1. 해당 게시글이 DB에 존재하는지 확인
        Post post = findBlogPost(id);

        // 2. 비밀번호 체크
        // post.checkPassword(requestDto.getPassword());

        // 3. 존재하면 post 수정
        post.update(requestDto); // DB 저장
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    public PostResponseDto deleteBlogPost(Integer id, PostRequestDto requestDto) {

        // 1. 해당 게시글이 DB에 존재하는지 확인
        Post post = findBlogPost(id);

        // 2. 비밀번호 체크
        // post.checkPassword(requestDto.getPassword());

        // 3. 존재하면 post 삭제
        postRepository.delete(post);

        return new PostResponseDto(true);
    }

    private Post findBlogPost(Integer id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
