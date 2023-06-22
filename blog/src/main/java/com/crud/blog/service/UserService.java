package com.crud.blog.service;

import com.crud.blog.dto.UserRequestDto;
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

    public UserResponseDto signup(UserRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // username 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 DB에 등록
        User user = new User(username, password);        
        User saveUser = userRepository.save(user);

        // Entity -> ResponseDto로 변환 후 반환
        UserResponseDto userResponseDto = new UserResponseDto(saveUser, "회원가입 성공", 200);

        return userResponseDto;
    }

    public UserResponseDto login(UserRequestDto requestDto, HttpServletResponse res) {

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장 후, Response 객체에 추가
        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token, res);

        return new UserResponseDto(user, "로그인 성공", 200);
    }
}
