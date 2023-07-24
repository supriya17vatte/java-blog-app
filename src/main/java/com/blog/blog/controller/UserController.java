package com.blog.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.dto.UserDto;
import com.blog.blog.entities.User;
import com.blog.blog.exceptions.UserNotFoundException;
import com.blog.blog.service.UserService;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    //POST- create user
    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user){
        UserDto userDto = userService.createUser(user);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }

    //PUT - update user
    @PutMapping("/update")
    public UserDto updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    //DELETE - delete user
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    //GET - 
    @GetMapping("/user/{id}")
    public User findById(@PathVariable int id)throws UserNotFoundException{
        return userService.getUserById(id);
    }

    @GetMapping("/users")
        public List<User> getAllUser(){
                 return userService.getAllUsers();
        }
    }
    
