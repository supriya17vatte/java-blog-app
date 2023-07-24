package com.blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    
}
