package com.crud.blog.service;

import com.crud.blog.dto.PostResponseDto;
import com.crud.blog.dto.UserRequestDto;
import com.crud.blog.dto.UserResponseDto;
import com.crud.blog.entity.User;
import com.crud.blog.jwt.JwtUtil;
import com.crud.blog.repository.UserRepository;
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

    public UserResponseDto signUp(UserRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // username 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // RequestDto -> Entity
        User user = new User(username, password);

        // DB 저장
        User saveUser = userRepository.save(user);

        // Entity -> ResponseDto로 변환 후 반환
        UserResponseDto userResponseDto = new UserResponseDto(saveUser, "회원가입 성공", 200);

        return userResponseDto;
    }
}
