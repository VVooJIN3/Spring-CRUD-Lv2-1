package com.crud.blog.controller;


import com.crud.blog.dto.UserRequestDto;
import com.crud.blog.dto.UserResponseDto;
import com.crud.blog.repository.UserRepository;
import com.crud.blog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 로깅에 사용
@RestController
@RequestMapping("/blog")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public UserResponseDto signup(@RequestBody @Valid UserRequestDto requestDto, HttpServletResponse res, BindingResult bindingResult) {

        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {

            // 검증 후 에러 출력
            for (FieldError fieldError : bindingResult.getFieldErrors()) {

                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }

            return null;
        }

        return userService.signup(requestDto, res);
    }

    @PostMapping("/auth/login")
    public UserResponseDto login(@RequestBody UserRequestDto requestDto, HttpServletResponse res) {

        return userService.login(requestDto, res);
    }
}
