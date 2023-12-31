package com.crud.blog.service;

import com.crud.blog.dto.PostRequestDto;
import com.crud.blog.dto.PostResponseDto;
import com.crud.blog.entity.Post;
import com.crud.blog.entity.User;
import com.crud.blog.repository.PostRepository;
import com.crud.blog.security.UserDetailsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    // 게시글 생성
    public PostResponseDto createBlogPost(PostRequestDto requestDto, User user) {
        Post post = postRepository.save(new Post(requestDto, user));
        return new PostResponseDto(post);
    }

    // 전체 게시글 조회
    public List<PostResponseDto> getBlogPosts() {

        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> responseDtoList = new ArrayList<>();

        for(Post post : postList) {
            responseDtoList.add(new PostResponseDto(post));
        }

        return responseDtoList;
    }

    // 선택한 게시글 조회
    public PostResponseDto getBlogPost(Integer id) {

        // 해당 게시글이 DB에 존재하는지 확인
        Post post = findBlogPost(id);

        // 게시글 조회
        return new PostResponseDto(post);
    }


    // 선택한 게시글 수정
    @Transactional
    public PostResponseDto updateBlogPost(Integer id, PostRequestDto requestDto, UserDetailsImpl userDetails) {

        // 1. 해당 게시글이 DB에 존재하는지 확인
        Post post = findBlogPost(id);
        // 2. 작성자(username) 체크
        post.checkUsername(userDetails.getUsername());

        // 3. 존재하면 post 수정
        post.update(requestDto); // DB 저장
        return new PostResponseDto(post);
    }

    public PostResponseDto deleteBlogPost(Integer id, UserDetailsImpl userDetails) {

        // 1. 해당 게시글이 DB에 존재하는지 확인
        Post post = findBlogPost(id);
        // 2. 작성자(username) 체크
        post.checkUsername(userDetails.getUsername());

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
