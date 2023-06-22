package com.crud.blog.controller;


import com.crud.blog.dto.UserRequestDto;
import com.crud.blog.dto.UserResponseDto;
import com.crud.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 로깅에 사용
@RestController
@RequestMapping("/blog")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public UserResponseDto signup(@RequestBody UserRequestDto requestDto) {

        return userService.signUp(requestDto);
    }
}
