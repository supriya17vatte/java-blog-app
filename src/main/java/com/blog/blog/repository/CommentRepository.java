package com.blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    
}
