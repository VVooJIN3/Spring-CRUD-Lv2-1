package com.crud.blog.dto;

import com.crud.blog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
        
    private LocalDateTime createdAt; // 생성시간
    private LocalDateTime modifiedAt; // 수정시간
    private Integer id; // id
    private String title; // 제목
    private String content; // 내용
    private String username; // 작성 유저

    private Boolean success; // 저장 완료인지 아닌지

    public PostResponseDto(Boolean success) {
        this.success = success;
    }

    public PostResponseDto(Post post) {

        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();

        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUsername();
    }
}
