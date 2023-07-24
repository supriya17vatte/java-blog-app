package com.blog.blog.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.dto.UserDto;
import com.blog.blog.entities.User;
import com.blog.blog.exceptions.UserNotFoundException;
import com.blog.blog.repository.UserRepository;
import com.blog.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto createUser(User user) {
      
       User savedUser =  userRepository.save(user);
       UserDto userDto = mapper.map(savedUser, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto updateUser(User user) {

        User existingUser = userRepository.findById(user.getId()).orElse(null);

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setAbout(user.getAbout());

        userRepository.save(existingUser);
        UserDto userDto = mapper.map(existingUser, UserDto.class);
        return userDto;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(int id) {
        //User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id));
      userRepository.deleteById(id);
       return  "User removed !!"+id;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {

       User user = userRepository.findById(id).orElse(null);
       if(user!= null){
            return user;
       }
       else{
        throw new UserNotFoundException("User not found with Id:"+id);
       }  
    }
    
}
