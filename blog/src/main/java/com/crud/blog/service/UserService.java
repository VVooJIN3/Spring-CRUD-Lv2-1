package com.crud.blog.service;

import com.crud.blog.dto.SignupRequestDto;
import com.crud.blog.dto.UserResponseDto;
import com.crud.blog.entity.User;
import com.crud.blog.jwt.JwtUtil;
import com.crud.blog.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDto signup(SignupRequestDto requestDto, HttpServletResponse res) {

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // username 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 DB에 등록
        userRepository.save(new User(username, password));

        // Entity -> ResponseDto로 변환 후 반환
        // 회원가입 성공 메세지, statusCode 전달
        UserResponseDto userResponseDto = new UserResponseDto("회원가입 성공", res.getStatus());

        return userResponseDto;
    }
}