package com.blog.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{

    Optional<User> findByEmail(String email);
    
}
