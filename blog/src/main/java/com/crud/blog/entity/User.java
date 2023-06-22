package com.crud.blog.entity;

import com.crud.blog.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9]){4,10}$")
    @Column(nullable = false, unique = true)
    private String username;

    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9]){8,15}$")
    @Column(nullable = false)
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
