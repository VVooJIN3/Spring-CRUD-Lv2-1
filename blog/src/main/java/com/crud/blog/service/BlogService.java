package com.crud.blog.service;

import com.crud.blog.dto.BlogRequestDto;
import com.crud.blog.dto.BlogResponseDto;
import com.crud.blog.entity.Blog;
import com.crud.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlogPost(BlogRequestDto requestDto) {

        // RequestDto -> Entity
        Blog post = new Blog(requestDto);

        // DB 저장
        Blog savePost = blogRepository.save(post); // 정보 저장

        // Entity -> ResponseDto로 변환 후 반환
        BlogResponseDto blogResponseDto = new BlogResponseDto(savePost);

        return blogResponseDto;
    }


    public List<BlogResponseDto> getBlogPosts() {

        // DB 조회
        return blogRepository.findAllByOrderByCreatedAtDesc().stream().map(BlogResponseDto::new).toList();
    }
    public BlogResponseDto getBlogPost(Integer id) {

        // 해당 게시글이 DB에 존재하는지 확인
        Blog blog = findBlogPost(id);

        // 게시글 조회
        return new BlogResponseDto(blog);
    }


    @Transactional
    public BlogResponseDto updateBlogPost(Integer id, BlogRequestDto requestDto) {

        // 1. 해당 게시글이 DB에 존재하는지 확인
        Blog blog = findBlogPost(id);

        // 2. 비밀번호 체크
        blog.checkPassword(requestDto.getPassword());

        // 3. 존재하면 blog 수정
        blog.update(requestDto); // DB 저장
        BlogResponseDto blogResponseDto = new BlogResponseDto(blog);

        return blogResponseDto;
    }

    public BlogResponseDto deleteBlogPost(Integer id, BlogRequestDto requestDto) {

        // 1. 해당 게시글이 DB에 존재하는지 확인
        Blog blog = findBlogPost(id);

        // 2. 비밀번호 체크
        blog.checkPassword(requestDto.getPassword());

        // 3. 존재하면 blog 삭제
        blogRepository.delete(blog);

        return new BlogResponseDto(true);
    }

    private Blog findBlogPost(Integer id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
