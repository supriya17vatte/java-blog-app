package com.blog.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.blog.entities.Category;
import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;

public interface PostRepository extends JpaRepository<Post,Integer> {

    //custom finder methods
    List<Post> findByUser(User user);  
    List<Post> findByCategory(Category category);  

    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);  
  
}
