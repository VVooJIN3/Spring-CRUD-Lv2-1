package com.crud.blog.dto;

import com.crud.blog.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private String username;
    private String password;
    private String msg;
    private Integer statusCode;

    public UserResponseDto(User user, String msg, Integer statusCode) {

        this.username = user.getUsername();
        this.password = user.getPassword();

        this.msg = msg;
        this.statusCode = statusCode;
    }
}
