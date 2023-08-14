package com.blog.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.dto.CommentDto;
import com.blog.blog.entities.Comment;
import com.blog.blog.entities.Post;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.repository.CommentRepository;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.service.CommentSerivce;


@Service
public class CommentServiceImpl implements CommentSerivce {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createCommnet(CommentDto commentDto, Integer postId) {
               Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
               Comment comment = this.modelMapper.map(commentDto, Comment.class);
               comment.setPost(post);
                Comment savedComment =this.commentRepository.save(comment);
                return this.modelMapper.map(savedComment, CommentDto.class) ;
    }

    @Override
    public void deleteComment(Integer commentId) {

    Comment comment=this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment Id", commentId));
      this.commentRepository.delete(comment);
    }
    
}
