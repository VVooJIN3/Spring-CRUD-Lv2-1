package com.crud.blog.repository;

import com.crud.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findAllByOrderByCreatedAtDesc();
}

