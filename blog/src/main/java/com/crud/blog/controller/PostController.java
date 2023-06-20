package com.crud.blog.controller;

import com.crud.blog.dto.PostRequestDto;
import com.crud.blog.dto.PostResponseDto;
import com.crud.blog.entity.Post;
import com.crud.blog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    private final Map<Integer, Post> blogMap = new HashMap<>();

    // 게시글 작성
    @PostMapping("/post")
    public PostResponseDto createBlogPost(@RequestBody PostRequestDto requestDto) {

        return postService.createBlogPost(requestDto);
    }

    // 전체 게시글 목록 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getBlogPosts() {

        return postService.getBlogPosts();
    }

    // 선택한 게시글 조회
    @GetMapping("/post/{id}")
    public PostResponseDto getBlogPost(@PathVariable Integer id) {

        return postService.getBlogPost(id);
    }

    // 선택한 게시글 수정
    @PutMapping("/post/{id}")
    public PostResponseDto updateBlogPost(@PathVariable Integer id, @RequestBody PostRequestDto requestDto) {

        return postService.updateBlogPost(id, requestDto);
    }

    // 선택한 게시글 삭제
    @DeleteMapping("/post/{id}")
    public PostResponseDto deleteBlogPost(@PathVariable Integer id, @RequestBody PostRequestDto requestDto) {

        return postService.deleteBlogPost(id, requestDto);
    }
}
