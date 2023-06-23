package com.crud.blog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostRequestDto {
    @NotBlank
    private String title; // 제목
    @NotBlank
    private String content; // 내용
}
