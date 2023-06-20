package com.crud.blog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title; // 제목
    private String content; // 내용
    private String author; // 작성자
    private String password; // 비밀번호
}
