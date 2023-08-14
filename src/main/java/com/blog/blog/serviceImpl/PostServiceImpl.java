package com.blog.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog.dto.PostDto;
import com.blog.blog.dto.PostResponse;
import com.blog.blog.entities.Category;
import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.repository.CategoryRepository;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.repository.UserRepository;
import com.blog.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        //get user id
   
       User user =  userRepository.findById(userId).orElse(null);
       Category category = categoryRepository.findById(categoryId).orElse(null);
    

        Post post =  modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost =postRepository.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post =  this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepository.save(post);
       return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
      Post post =  this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy ,String sortDir) {

       Sort sort =(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
    //    if(sortDir.equalsIgnoreCase("asc")){
    //     sort = Sort.by(sortBy).ascending();
    //    }
    //    else{
    //     sort = Sort.by(sortBy).descending();
    //    }
        Pageable page =  PageRequest.of(pageNumber, pageSize, sort);
       // Page<Post> findAll = this.p
       Page<Post> pagePost= this.postRepository.findAll(page);
       List<Post> allPosts = pagePost.getContent();
       List<PostDto> postDtos=allPosts.stream().map((post) ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
       
       PostResponse postResponse = new PostResponse();

       postResponse.setContent(postDtos);
       postResponse.setPageNumber(pagePost.getNumber());
       postResponse.setPageSize(pagePost.getSize());
       postResponse.setTotalElement(pagePost.getTotalElements());
       postResponse.setTotalPages(pagePost.getTotalPages());
       postResponse.setLastPage(pagePost.isLast());
       return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
       Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "Id", categoryId));
        
        //Category returnCategoryId = this.categoryRepository.findById(categoryId).orElse(null);
        List<Post> posts = this.postRepository.findByCategory(category);
         return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    
    }


    // @Override
    // public List<PostDto> getPostByCategory(Integer categoryId) {
    //     Category category = categoryRepository.findById(categoryId).orElse(null);
    //             //.orElseThrow(() -> new ResourceNotFoundException("category", "Id", categoryId));
    //     List<Post> listPosts = postRepository.findByCategory(category);
    //     return listPosts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

    // }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "Id", userId));
           List<Post> posts =  this.postRepository.findByUser(user);
           List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> listOfPosts =postRepository.searchByTitle("%"+keyword+"%");
           List<PostDto> postDtos = listOfPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
       return postDtos;
    }
    
}
