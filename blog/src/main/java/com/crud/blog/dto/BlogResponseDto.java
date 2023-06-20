package com.crud.blog.dto;

import com.crud.blog.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
        
    private LocalDateTime createdAt; // 생성시간
    private LocalDateTime modifiedAt; // 수정시간
    private Integer id; // id
    private String title; // 제목
    private String content; // 내용
    private String author; // 작성자

    private Boolean success; // 저장 완료인지 아닌지

    public BlogResponseDto(Boolean success) {
        this.success = success;
    }

    public BlogResponseDto(Blog blog) {
        this.createdAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();

        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.author = blog.getAuthor();
    }
}
