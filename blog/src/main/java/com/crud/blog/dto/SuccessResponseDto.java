package com.crud.blog.dto;

import com.crud.blog.entity.Blog;
import lombok.Getter;

@Getter
public class SuccessResponseDto {
    private Boolean success; // 저장 완료인지 아닌지
    
    public SuccessResponseDto(Boolean success) {
        this.success = success;
    }
}
