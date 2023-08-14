package com.blog.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.dto.CommentDto;
import com.blog.blog.exceptions.ApiResponse;
import com.blog.blog.service.CommentSerivce;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentSerivce commentSerivce;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createCommnet(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
           CommentDto createComment =  this.commentSerivce.createCommnet(commentDto, postId);
           return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED); 
         }

    @DeleteMapping("/comment/{commetId}")
    public ResponseEntity<ApiResponse> deleteCommnet(@PathVariable Integer commetId){
            this.commentSerivce.deleteComment(commetId);
           return new ResponseEntity<ApiResponse>( new ApiResponse("Comment deleted successfully !!", true),HttpStatus.OK); 
         }
}
