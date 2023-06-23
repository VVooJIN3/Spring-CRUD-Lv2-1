package com.crud.blog.dto;

import com.crud.blog.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    //private String username;
    //private String password;
    private String msg;
    private Integer statusCode;

    public UserResponseDto(String msg, Integer statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
