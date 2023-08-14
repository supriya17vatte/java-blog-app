package com.blog.blog.service;

import com.blog.blog.dto.CommentDto;

public interface CommentSerivce {
    
    CommentDto createCommnet(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
