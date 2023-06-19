package com.crud.blog.entity;

import com.crud.blog.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "post") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Blog extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // id
    @Column(name = "title", nullable = false)
    private String title; // 제목
    @Column(name = "content", nullable = false)
    private String content; // 내용
    @Column(name = "author", nullable = false)
    private String author; // 작성자
    @Column(name = "password", nullable = false, length = 12)
    private String password; // 비밀번호


    public Blog(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }

    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = requestDto.getAuthor();
    }

}
