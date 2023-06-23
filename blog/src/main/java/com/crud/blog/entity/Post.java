package com.crud.blog.entity;

import com.crud.blog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "post") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // id
    @Column(name = "title", nullable = false)
    private String title; // 제목
    @Column(name = "content", nullable = false)
    private String content; // 내용
    @Column(name = "username", nullable = false)
    private String username; // 작성한 유저


    public Post(PostRequestDto requestDto, User user) {

        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();

        this.username = user.getUsername();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
