package com.crud.blog.controller;

import com.crud.blog.dto.BlogRequestDto;
import com.crud.blog.dto.BlogResponseDto;
import com.crud.blog.entity.Blog;
import com.crud.blog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    private final Map<Integer, Blog> blogMap = new HashMap<>();

    // 게시글 작성
    @PostMapping("/post")
    public BlogResponseDto createBlogPost(@RequestBody BlogRequestDto requestDto) {

        return blogService.createBlogPost(requestDto);
    }

    // 전체 게시글 목록 조회
    @GetMapping("/posts")
    public List<BlogResponseDto> getBlogPosts() {

        return blogService.getBlogPosts();
    }

    // 선택한 게시글 조회
    @GetMapping("/post/{id}")
    public BlogResponseDto getBlogPost(@PathVariable Integer id) {

        return blogService.getBlogPost(id);
    }

    // 선택한 게시글 수정
    @PutMapping("/post/{id}")
    public BlogResponseDto updateBlogPost(@PathVariable Integer id, @RequestBody BlogRequestDto requestDto) {

        return blogService.updateBlogPost(id, requestDto);
    }

    // 선택한 게시글 삭제
    @DeleteMapping("/post/{id}")
    public BlogResponseDto deleteBlogPost(@PathVariable Integer id, @RequestBody BlogRequestDto requestDto) {

        return blogService.deleteBlogPost(id, requestDto);
    }
}
