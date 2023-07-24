package com.blog.blog.service;

import java.util.List;
import com.blog.blog.dto.UserDto;
import com.blog.blog.entities.User;
import com.blog.blog.exceptions.UserNotFoundException;

public interface UserService {

    //create user
   public UserDto createUser(User user);  
   //update user
    public UserDto updateUser(User user); 
    //get by id
    public User getUserById(int id) throws UserNotFoundException;  
    //get all users
    public List <User> getAllUsers();
    //delete user
    public String deleteUser(int id);

 
}
