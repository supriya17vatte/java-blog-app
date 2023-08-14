package com.blog.blog.service;

import java.util.List;

import com.blog.blog.dto.PostDto;
import com.blog.blog.dto.PostResponse;
import com.blog.blog.exceptions.UserNotFoundException;

public interface PostService {
    
    // create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) throws UserNotFoundException;

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all post
    PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    //get signle post
    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get all post by user
    List<PostDto> getPostByUser(Integer userId);

    //serach post
    List<PostDto> searchPosts(String keyword);
}
